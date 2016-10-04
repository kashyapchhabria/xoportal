package com.xo.web.ext.tableau;

import java.io.IOException;
import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.fasterxml.jackson.databind.JsonNode;
import com.xo.web.core.XOException;
import com.xo.web.ext.tableau.models.Site;
import com.xo.web.ext.tableau.models.TableauProject;
import com.xo.web.ext.tableau.models.TableauServerResponse;
import com.xo.web.ext.tableau.models.TableauSite;
import com.xo.web.ext.tableau.models.TableauUser;
import com.xo.web.ext.tableau.models.TableauView;
import com.xo.web.ext.tableau.models.TableauWorkbook;
import com.xo.web.ext.tableau.models.User;
import com.xo.web.ext.tableau.models.View;
import com.xo.web.ext.tableau.models.ViewGroup;
import com.xo.web.ext.tableau.models.WorkBook;
import com.xo.web.ext.tableau.models.dao.TableauProjectDao;
import com.xo.web.ext.tableau.models.dao.TableauProjectDaoImpl;
import com.xo.web.ext.tableau.models.dao.TableauSiteDao;
import com.xo.web.ext.tableau.models.dao.TableauSiteDaoImpl;
import com.xo.web.ext.tableau.models.dao.TableauUserDao;
import com.xo.web.ext.tableau.models.dao.TableauUserDaoImpl;
import com.xo.web.ext.tableau.models.dao.TableauViewDao;
import com.xo.web.ext.tableau.models.dao.TableauViewDaoImpl;
import com.xo.web.ext.tableau.models.dao.TableauWorkbookDao;
import com.xo.web.ext.tableau.models.dao.TableauWorkbookDaoImpl;
import com.xo.web.ext.tableau.models.dao.ViewGroupDao;
import com.xo.web.ext.tableau.models.dao.ViewGroupDaoImpl;
import com.xo.web.mgr.XoConfigInstanceLogic;
import com.xo.web.models.dao.GenericDAO;
import com.xo.web.util.XoAppConfigKeys;
import com.xo.web.util.XoUtil;
import com.xo.web.viewdtos.ConfigurationDto;

import play.Logger;
import play.libs.Json;

public class TableauConnector {

	public static final String TABLEAU_DEFAULT = "default";
	private static final String TABLEAU_CONFIG_NAME = "Tableau Sync";
	private static final String REGEX_VIEW_NAME_STD = "^(\\d{2})(\\d{3})([F|f|T|t])(.*)";
	// Create a Pattern object
    private static final Pattern VIEW_NAME_PATTERN = Pattern.compile(REGEX_VIEW_NAME_STD);

	private final XoConfigInstanceLogic configInstanceLogic = new XoConfigInstanceLogic();

	public final void updatePortalTableauReports(String tableauHost, 
			String userName, String password, final String siteName, String siteContentUrl) throws Exception {

		if(XoUtil.isNotNull(siteName)) {

			TableauSiteDao tableauSiteDao = new TableauSiteDaoImpl();
			TableauUserDao tableauUserDao = new TableauUserDaoImpl();
			TableauProjectDao tableauProjectDao = new TableauProjectDaoImpl();
			TableauWorkbookDao tableauWorkbookDao = new TableauWorkbookDaoImpl();
			TableauViewDao tableauViewDao = new TableauViewDaoImpl();
			ViewGroupDao viewGroupDao = new ViewGroupDaoImpl();

			Matcher viewNameMatcher = null;
			int groupIndex = -1;
			int viewGroupId = -1;
			int displayOrder = -1;
			boolean isDashboard = false;
			String displayName = null;
			
			Map<Integer, ViewGroup> viewGroups = viewGroupDao.findAllAsMap();
			
			TableauServerResponse tsResponse = TableauRESTConnector.signIn(tableauHost, userName, password, siteContentUrl);			
			String authToken = null;
			if(tsResponse.credentials != null && tsResponse.credentials.token != null) {
				authToken = tsResponse.credentials.token;
			} else {
				throw (new XOException("Could not login into tableau. Please contact your admin."));
			}

			Site site = new Site();
			site.siteName = siteName;
			site.id = tsResponse.credentials.site.id;
			site.contentUrl = siteContentUrl;
			
			TableauRESTConnector.getSiteDetailsByName(tableauHost, site, authToken);
			TableauSite tableauSite = tableauSiteDao.findByNameAndContentUrl(siteName, siteContentUrl);

			if(tableauSite == null) {
				tableauSite = new TableauSite(site);
				tableauSiteDao.save(tableauSite);
			}

			List<User> users = TableauRESTConnector.getUsersDetailsBySite(tableauHost, site, authToken);

			List<String> latestTableauUsers = new ArrayList<String>();
			List<String> latestTableauProjects = new ArrayList<String>();
			List<String> latestTableauWorkbooks = new ArrayList<String>();
			List<String> latestTableauViews = new ArrayList<String>();

			Map<String, TableauProject> tableauProjectsMap = new HashMap<String, TableauProject>();
			Map<String, TableauUser> tableauUsersMap = new HashMap<String, TableauUser>();
			Map<String, TableauWorkbook> tableauWorkbooksMap = new HashMap<String, TableauWorkbook>();
			Map<String, TableauView> tableauViewsMap = new HashMap<String, TableauView>();

			if(XoUtil.hasData(users)) {

				Set<TableauUser> tableauUsers = tableauSite.getTableauUsers();
				for(TableauUser tableauUser : tableauUsers){
					tableauUsersMap.put(tableauUser.getTableauUserId(), tableauUser);
				}

				// verification and addition of new users 
				for(User user : users) {

					if(!user.name.equalsIgnoreCase(userName)) {
						continue;
					}
					latestTableauUsers.add(user.id);
					TableauUser tableauUser = tableauUsersMap.get(user.id);
					if(tableauUser == null) {
						tableauUser = new TableauUser(user);
						tableauUser.setTableauSite(tableauSite);
						tableauUsers.add(tableauUser);
						tableauUserDao.persist(tableauUser);
						tableauUsersMap.put(user.id, tableauUser);
					}

					List<WorkBook> workbooks = TableauRESTConnector.getWorkBooksForUser(tableauHost, site, user, authToken);
					if(XoUtil.hasData(workbooks)) {

						Set<TableauProject> tableauProjects = tableauUser.getTableauProjects();

						if(XoUtil.hasData(tableauProjects)) {
							for(TableauProject tableauProject : tableauProjects) {
								tableauProjectsMap.put(tableauProject.getTableauProjectId(), tableauProject);
								if(XoUtil.hasData(tableauProject.getTableauWorkbooks())) {
									for(TableauWorkbook tableauWorkbook : tableauProject.getTableauWorkbooks()) {
										tableauWorkbooksMap.put(tableauWorkbook.getTableauWorkbookId(), tableauWorkbook);
										if(XoUtil.hasData(tableauWorkbook.getTableauViews())) {
											for(TableauView tableauView : tableauWorkbook.getTableauViews()) {
												tableauViewsMap.put(tableauView.getTableauViewId(), tableauView);
											}
										}
									}
								}
							}
						}

						// verification and addition of new workbooks 
						for(WorkBook workbook : workbooks){

							if(!workbook.project.name.equalsIgnoreCase(TABLEAU_DEFAULT)) {

								latestTableauProjects.add(workbook.project.id);
								TableauProject tableauProject = tableauProjectsMap.get(workbook.project.id);

								Set<TableauWorkbook> tableauWorkbooks = null;
								if(tableauProject == null) {
									tableauProject = new TableauProject(workbook.project);
									tableauProject.setTableauUser(tableauUser);
									tableauProjectDao.save(tableauProject);
									tableauProjectsMap.put(workbook.project.id, tableauProject);
								}

								latestTableauWorkbooks.add(workbook.id);
								tableauWorkbooks = tableauProject.getTableauWorkbooks();
								TableauWorkbook tableauWorkbook = tableauWorkbooksMap.get(workbook.id);
								Set<TableauView> tableauViews = null;
								if(tableauWorkbook == null) {
									tableauWorkbook = new TableauWorkbook(workbook);
									tableauWorkbook.setTableauProject(tableauProject);
									tableauWorkbooks.add(tableauWorkbook);
									tableauWorkbookDao.save(tableauWorkbook);
									tableauWorkbooksMap.put(workbook.id, tableauWorkbook);
								}
								tableauViews = tableauWorkbook.getTableauViews();

								List<View> views = TableauRESTConnector.getViewsForWorkbook(tableauHost, site, workbook, authToken);
								if(XoUtil.hasData(views)) {

									// addition of new reports 
									for(View view : views) {
										latestTableauViews.add(view.id);
										TableauView tableauView = tableauViewsMap.get(view.id);
										if(tableauView == null) {
											tableauView = new TableauView(view);
											tableauView.setActive(true);
											tableauView.setTableauWorkbook(tableauWorkbook);
											tableauViews.add(tableauView);

											// Validating & Extracting report group settings.
											String tempViewName = view.name;
											viewNameMatcher = VIEW_NAME_PATTERN.matcher(tempViewName.trim());
											groupIndex = 0;
											String tempValueHolder = null;
											if(viewNameMatcher.find()){

												tempValueHolder = viewNameMatcher.group(++groupIndex);
												viewGroupId = new Integer(tempValueHolder);

												tempValueHolder = viewNameMatcher.group(++groupIndex);
												displayOrder = new Integer(tempValueHolder);

												tempValueHolder = viewNameMatcher.group(++groupIndex);
												isDashboard = "T".equalsIgnoreCase(tempValueHolder);

												displayName = viewNameMatcher.group(++groupIndex);

												tableauView.setDisplayName(displayName.trim());
												tableauView.setDashboard(isDashboard);
												tableauView.setDisplayOrder(displayOrder);
												ViewGroup viewGroup = viewGroups.get(viewGroupId);
												if(viewGroup != null) {
													viewGroup.getTableauViews().add(tableauView);
													tableauView.setViewGroup(viewGroup);
												}
											}
											tableauViewDao.save(tableauView);
											tableauViewsMap.put(view.id, tableauView);
										}
									}
								}
							}
						}
					}
				}
				//removing deleted entries from table
				deleteEntities(tableauViewDao, latestTableauViews, tableauViewsMap);
				deleteEntities(tableauWorkbookDao, latestTableauWorkbooks, tableauWorkbooksMap);
				deleteEntities(tableauProjectDao, latestTableauProjects, tableauProjectsMap);
				deleteEntities(tableauUserDao, latestTableauUsers, tableauUsersMap);
				tableauSiteDao.merge(tableauSite);
			}
		}
	}

	private final <T, ID extends Serializable> void deleteEntities(GenericDAO<T, ID> dao, 
			List<String> latestLists, 
			Map<String, T> oldEntitiesMap) {
		// identifying the deleted entities
		for(String entityId : latestLists) {
			oldEntitiesMap.remove(entityId);
		}
		// Some entities are deleted in tableau so we need to remove those in portal as well.
		if(oldEntitiesMap.size() > 0) {
			for(Entry<String, T> viewEntry: oldEntitiesMap.entrySet()) {
				dao.remove((T) viewEntry.getValue());
			}
		}
	}

	public final String loadConfig() throws XOException {
		ConfigurationDto configurationDto = null;
		try {
			configurationDto = configInstanceLogic.loadConfig(TABLEAU_CONFIG_NAME);
			Logger.info("Successfully Loaded Configurations");
		} catch (Exception e) {
			Logger.error("Failed Loading Configurations");
			throw new XOException(e);
		}
		return configurationDto.configJson;
	}

	public final void syncPortalData(String configJson) throws Exception {
		try {
			JsonNode obj = Json.parse(configJson);
			final String tableauUserName = obj.findPath(XoAppConfigKeys.TABLEAU_REST_USER).asText();
			final String tableauPassword = obj.findPath(XoAppConfigKeys.TABLEAU_REST_PASSWORD).asText();
			final String tableauInternalHost = obj.findPath(XoAppConfigKeys.TABLEAU_INTERNAL_HOST).asText();
			final String siteName = obj.findPath(XoAppConfigKeys.TABLEAU_SITENAME).asText();
			final String siteContentUrl = obj.findPath(XoAppConfigKeys.TABLEAU_SITE_CONTENT_URL).asText();
			this.updatePortalTableauReports(tableauInternalHost, tableauUserName, tableauPassword, siteName, siteContentUrl);
		} catch (InvalidKeyException | NoSuchAlgorithmException
				| InvalidKeySpecException | NoSuchPaddingException
				| IllegalBlockSizeException | BadPaddingException | IOException
				| ParserConfigurationException | SAXException e) {
			Logger.error("Tableau synch error.", e);
			throw e;
		} catch(Exception e) {
			Logger.error("Tableau synch error.", e);
			throw e;
		}
	}

	public final String getTicket(String tableauInternalHost, String tableauUserName, String clientIP, String siteContentUrl) {
		String tableauTicket = null;
		try{
			//tableauUserName = new Domain().name + "\\" + tableauUserName;
			tableauTicket = TableauRESTConnector.getTicket(tableauInternalHost, tableauUserName, clientIP, siteContentUrl);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return tableauTicket;
	}

}
