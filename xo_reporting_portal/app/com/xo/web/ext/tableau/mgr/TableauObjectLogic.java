package com.xo.web.ext.tableau.mgr;


import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.databind.JsonNode;
import com.xo.web.core.XODAOException;
import com.xo.web.core.XOException;
import com.xo.web.ext.tableau.TableauConnector;
import com.xo.web.ext.tableau.TableauRESTConnector;
import com.xo.web.ext.tableau.models.TableauProject;
import com.xo.web.ext.tableau.models.TableauSite;
import com.xo.web.ext.tableau.models.TableauUser;
import com.xo.web.ext.tableau.models.TableauView;
import com.xo.web.ext.tableau.models.TableauWorkbook;
import com.xo.web.ext.tableau.models.ViewGroup;
import com.xo.web.ext.tableau.models.dao.TableauProjectDao;
import com.xo.web.ext.tableau.models.dao.TableauProjectDaoImpl;
import com.xo.web.ext.tableau.models.dao.TableauSiteDao;
import com.xo.web.ext.tableau.models.dao.TableauSiteDaoImpl;
import com.xo.web.ext.tableau.models.dao.ViewGroupDao;
import com.xo.web.ext.tableau.models.dao.ViewGroupDaoImpl;
import com.xo.web.mgr.XoClientJobConfigLogic;
import com.xo.web.mgr.XoConfigInstanceLogic;
import com.xo.web.models.system.XoClient;
import com.xo.web.models.system.XoClientJobConfig;
import com.xo.web.models.util.DisplayOrderComparator;
import com.xo.web.util.XoAppConfigKeys;
import com.xo.web.util.XoMailContentProvider;
import com.xo.web.util.XoMailSender;
import com.xo.web.util.XoUtil;
import com.xo.web.viewdtos.BreadCrumbDTO;
import com.xo.web.viewdtos.ConfigurationDto;
import com.xo.web.viewdtos.DashboardDTO;
import com.xo.web.viewdtos.MailDto;
import com.xo.web.viewdtos.MenuDTO;
import com.xo.web.viewdtos.ScreenDTO;
import com.xo.web.viewdtos.XoClientJobConfigDto;

import play.Logger;
import play.i18n.Messages;
import play.libs.Json;

public class TableauObjectLogic {

	private static final String MAIN_DASHBOARD_NAME = "01001t";
	private static final String TABLEAU_REPORT_URL_PARAMS = "?:embed=y&:display_count=no&:toolbar=no";
	private static final String TABLEAU_SYNC_CLASS_NAME = "com.xo.web.work.scheduler.XoTableauReportSynchronizer";
	/*private static final String SYMPOL_QUESTION = "?";
	private static final String SYMPOL_AMPERSAND = "&";
	private static final String SYMPOL_EQUAL = "=";*/
	public static final String TABLEAU_MULTI_SITE = "t";
	private static final String SYMPOL_FORWARD_SLASH = "/";
	
	private static final String XOAPP_SUPPORT_TEAM = "Xoapp Support Team";
	private static final String CONTACT_EMAILS = "contact_emails";
	private static final String RECONFIGURE_REPORTS_EMAIL_TEMPLATE = "com.xo.web.views.html.email.configure_reports"; 

	private final TableauProjectDao tableauProjectDao = new TableauProjectDaoImpl();
	private final TableauSiteDao tableauSiteDao = new TableauSiteDaoImpl();
	private final ViewGroupDao viewGroupDao = new ViewGroupDaoImpl();
	private final XoConfigInstanceLogic XOCONFIG_INSTANCE_LOGIC = new XoConfigInstanceLogic();
	private final XoMailContentProvider xoMailContentProvider = new XoMailContentProvider();
	
	private final XoClientJobConfigLogic clientJobConfigLogic = new XoClientJobConfigLogic();
	private XoClientJobConfig clientJobConfig = null;
	
	private final TableauConnector tableauConnector;
	
	private String tableauUserName;
	public String tableauPublicHost;
	private String tableauInternalHost;
	private String userDomain = "corp.xoanonanalytics.net";
	private String siteName;
	private String siteContentUrl;
	private String clientIP;

	public TableauObjectLogic() {
		tableauConnector = new TableauConnector();
		try {
			final String TABLEAU_CONFIG_JSON = tableauConnector.loadConfig();
			this.setTableauConfigs(TABLEAU_CONFIG_JSON);
		} catch (XOException e) {
			Logger.error("Error while initizing the tableau object with configuration.", e);
		}
	}

	private void setTableauConfigs(final String tableauConfigJson) throws XOException {
		try {
			JsonNode obj = Json.parse(tableauConfigJson);
			this.tableauUserName = obj.findPath(XoAppConfigKeys.TABLEAU_REST_USER).asText();
			this.tableauPublicHost = obj.findPath(XoAppConfigKeys.TABLEAU_PUBLIC_HOST).asText();
			this.tableauInternalHost = obj.findPath(XoAppConfigKeys.TABLEAU_INTERNAL_HOST).asText();
			this.siteName = obj.findPath(XoAppConfigKeys.TABLEAU_SITENAME).asText();
			this.clientIP = obj.findPath(XoAppConfigKeys.TABLEAU_CLIENT_IP).asText();
			this.siteContentUrl = obj.findPath(XoAppConfigKeys.TABLEAU_SITE_CONTENT_URL).asText();
			this.userDomain = obj.findPath(XoAppConfigKeys.TABLEAU_REST_USER_DOMAIN).asText();
			if(!XoUtil.isNotNull(this.userDomain)) {
				this.userDomain = "corp.xoanonanalytics.net";
			}			
		}  catch (Exception e) {
			Logger.error("Error while initizing the tableau object with configuration.", e);
		}
	}

	public String loadTableauConfig(XoClient xoClient) throws XODAOException {
		String configJson = null;
		if(xoClient != null) {
			Set<XoClientJobConfigDto> clientJobConfigs = clientJobConfigLogic.findAllClientJobs(xoClient.getClientId());
			if(XoUtil.hasData(clientJobConfigs)) {
				for(XoClientJobConfigDto clientJobConfig : clientJobConfigs) {
					if(TABLEAU_SYNC_CLASS_NAME.equalsIgnoreCase(clientJobConfig.jobClassName)) {
						configJson = clientJobConfig.configJson;
						break;
					}
				}
			}
		}
		return configJson;
	}

	public List<BreadCrumbDTO> buildBreadCrumbsGroup(DashboardItemEnum dashboardItemEnum,
													 TableauProject tableauProject,
													 ViewGroup viewGroup,
													 TableauView tableauView) {
		List<BreadCrumbDTO> breadCrumbDTOs = new ArrayList<BreadCrumbDTO>();
		TableauView viewItem = null;
		if(XoUtil.isNotNull(viewGroup)) {
			Set<TableauView> tableauViews = viewGroup.getTableauViews();
			for(TableauView tabView : tableauViews) {
				if(tabView.isActive()){
					if (tabView.isDashboard()) {
						viewItem = tabView;
						break;
					}
				}
			}
		}
		String applicationContext = XoUtil.getApplicationContext();
		switch(dashboardItemEnum) {
			case VIEW :{
				BreadCrumbDTO breadcrumbDto = new BreadCrumbDTO();
				breadcrumbDto.name = tableauView.getDisplayName();
				breadcrumbDto.pageUrl = buildLinkUrl(DashboardItemEnum.VIEW , null,viewItem.getTableauWorkbook().getTableauWorkbookId(),tableauView.getTableauViewId(), applicationContext);;
				breadCrumbDTOs.add(breadcrumbDto);
			}
			case WORKBOOK :{
				BreadCrumbDTO breadcrumbDto = new BreadCrumbDTO();
				breadcrumbDto.name = viewGroup.getGroupName();
				if(XoUtil.isNotNull(viewItem)){
					breadcrumbDto.pageUrl = buildLinkUrl(DashboardItemEnum.VIEW , null,viewItem.getTableauWorkbook().getTableauWorkbookId(),viewItem.getTableauViewId(), applicationContext);
				}
				breadCrumbDTOs.add(breadcrumbDto);
			}
			case DASHBOARD :{
				BreadCrumbDTO breadcrumbDto = new BreadCrumbDTO();
				breadcrumbDto.name = "Dashboard";
				breadcrumbDto.pageUrl = buildLinkUrl(null, null, null,null, applicationContext);
				breadCrumbDTOs.add(breadcrumbDto);
			}
		}
		return breadCrumbDTOs;
	}

	public DashboardDTO buildDashboardDto(DashboardItemEnum dashboardItemEnum,
										  TableauProject tableauProject,
										  TableauWorkbook tableauWorkbook,
										  TableauView tableauView,
										  String applicationContext) {

		DashboardDTO dashboardDTO = new DashboardDTO();
		String workbookName = tableauWorkbook.getName();
		String viewName = tableauView.getName();
		String displayName = tableauView.getDisplayName();
		switch(dashboardItemEnum) {
			case PROJECT :
				dashboardDTO.name = displayName;
				dashboardDTO.pageUrl = buildLinkUrl(DashboardItemEnum.PROJECT ,null, tableauWorkbook.getTableauWorkbookId(),tableauView.getTableauViewId(), applicationContext);
				dashboardDTO.imageUrl = buildTableauLinkUrl(workbookName, viewName);
				break;
			case VIEW :
				dashboardDTO.name = displayName;
				dashboardDTO.pageUrl = buildLinkUrl(DashboardItemEnum.VIEW ,null, tableauWorkbook.getTableauWorkbookId(),tableauView.getTableauViewId(), applicationContext);
				dashboardDTO.imageUrl = buildTableauLinkUrl(workbookName, viewName);
				break;
		}
		return dashboardDTO;
	}

	public MenuDTO buildMenu(DashboardItemEnum dashboarditem,TableauProject tableauProject,
							 TableauWorkbook tableauWorkbook,TableauView tableauView, String applicationContext){
		MenuDTO menuDTO = new MenuDTO();
		TableauView viewItem = new TableauView();
		if(XoUtil.isNotNull(tableauWorkbook)) {
			Set<TableauView> tableauViews = tableauWorkbook.getTableauViews();
			if(XoUtil.hasData(tableauViews)){
				viewItem = tableauViews.iterator().next();
			}
		}
		switch(dashboarditem) {
			case DASHBOARD :
				menuDTO.name = tableauProject.getDisplayName();
				menuDTO.pageUrl = this.buildLinkUrl(DashboardItemEnum.PROJECT,tableauProject.getTableauProjectId(),null,null, applicationContext);
				break;
			case PROJECT :
				menuDTO.name = tableauWorkbook.getDisplayName();
				menuDTO.pageUrl = this.buildLinkUrl(DashboardItemEnum.VIEW,null,tableauWorkbook.getTableauWorkbookId(),viewItem.getTableauViewId(), applicationContext);
				break;
			case VIEW :
				menuDTO.name = tableauView.getDisplayName();
				menuDTO.pageUrl = this.buildLinkUrl(DashboardItemEnum.VIEW,null,tableauWorkbook.getTableauWorkbookId(),tableauView.getTableauViewId(), applicationContext);
				break;
		}
		return menuDTO;
	}



	public MenuDTO buildGroupMenu(DashboardItemEnum dashboarditem,TableauProject tableauProject,
								  ViewGroup viewGroup, TableauView tableauView, String applicationContext){
		MenuDTO menuDTO = new MenuDTO();
		TableauView viewItem = null;
		if(XoUtil.isNotNull(viewGroup)) {
			Set<TableauView> tableauViews = viewGroup.getTableauViews();
			for(TableauView tableauView1 : tableauViews) {
				if(tableauView1.isActive()) {
					if (tableauView1.isDashboard()) {
						viewItem = tableauView1;
						break;
					}
				}
			}
		}

		switch(dashboarditem) {
			case DASHBOARD :
				menuDTO.name = tableauProject.getDisplayName();
				menuDTO.pageUrl = this.buildLinkUrl(DashboardItemEnum.PROJECT,tableauProject.getTableauProjectId(),null,null, applicationContext);
				break;
			case PROJECT :
				menuDTO.name = viewGroup.getGroupName();
				menuDTO.pageUrl = this.buildLinkUrl(DashboardItemEnum.VIEW,null, viewItem.getTableauWorkbook().getTableauWorkbookId(), viewItem.getTableauViewId(), applicationContext);
				break;
			case VIEW :
				menuDTO.name = tableauView.getDisplayName();
				menuDTO.pageUrl = this.buildLinkUrl(DashboardItemEnum.VIEW,null, tableauView.getTableauWorkbook().getTableauWorkbookId(), tableauView.getTableauViewId(), applicationContext);
				break;
		}
		return menuDTO;
	}


	public String buildLinkUrl(DashboardItemEnum childpage,String projectId,String workbookId, String viewId, String applicationContext) {
		StringBuilder linkUrl = new StringBuilder(applicationContext);
		linkUrl.append(SYMPOL_FORWARD_SLASH);
		linkUrl.append("dashboard");
		if(XoUtil.isNotNull(childpage)){
			linkUrl.append(SYMPOL_FORWARD_SLASH);
			linkUrl.append(childpage.toString().toLowerCase());
		}
		if(XoUtil.isNotNull(projectId)) {
			linkUrl.append(SYMPOL_FORWARD_SLASH);
			linkUrl.append(projectId);
		}
		if(XoUtil.isNotNull(workbookId)) {
			linkUrl.append(SYMPOL_FORWARD_SLASH);
			linkUrl.append(workbookId);
		}
		if(XoUtil.isNotNull(viewId)) {
			linkUrl.append(SYMPOL_FORWARD_SLASH);
			linkUrl.append(viewId);
		}
		return linkUrl.toString();
	}

	/*private void appendUrlParam(StringBuilder url, String key, String value) {
		if(XoUtil.isNotNull(url)) {
			StringBuilder urlBuilder = new StringBuilder(url);
			if(urlBuilder.indexOf(SYMPOL_QUESTION) < 0) {
				urlBuilder.append(SYMPOL_QUESTION);
			} else if(!urlBuilder.toString().endsWith(SYMPOL_AMPERSAND)) {
				urlBuilder.append(SYMPOL_AMPERSAND);
			}
			urlBuilder.append(key);
			urlBuilder.append(SYMPOL_EQUAL);
			urlBuilder.append(value);
			
		}
	}*/

	public String buildTableauLinkUrl(String workbookName, String viewName) {

		String urlWorkbookName = workbookName.replaceAll(" ", "");
		String urlViewName = viewName.replaceAll(" ", "");

		StringBuilder tableauImageurl = new StringBuilder();
		tableauImageurl.append(this.tableauPublicHost);
		tableauImageurl.append(TableauRESTConnector.URL_QUERY_TRUSTED_IP_TICKET);
		tableauImageurl.append(SYMPOL_FORWARD_SLASH);
		tableauImageurl.append(tableauConnector.getTicket(this.tableauInternalHost, /*this.userDomain + "\\" + */this.tableauUserName, this.clientIP, this.siteContentUrl));
		tableauImageurl.append(SYMPOL_FORWARD_SLASH);
		tableauImageurl.append(TABLEAU_MULTI_SITE);
		tableauImageurl.append(SYMPOL_FORWARD_SLASH);
		tableauImageurl.append(this.siteContentUrl);
		tableauImageurl.append(SYMPOL_FORWARD_SLASH);
		tableauImageurl.append("views");
		tableauImageurl.append(SYMPOL_FORWARD_SLASH);
		tableauImageurl.append(urlWorkbookName);
		tableauImageurl.append(SYMPOL_FORWARD_SLASH);
		tableauImageurl.append(urlViewName);
		tableauImageurl.append(TABLEAU_REPORT_URL_PARAMS);

		return tableauImageurl.toString();
	}

	public void loadProjectDashboard(final String projectId, final ScreenDTO screenDto) {
		if(XoUtil.isNotNull(projectId)) {
			TableauProject tableauProject = this.tableauProjectDao.find(projectId);
			Set<TableauWorkbook> workbooks = tableauProject.getTableauWorkbooks();
			if(XoUtil.hasData(workbooks)) {
				String applicationContext = XoUtil.getApplicationContext();
				for(TableauWorkbook tableauWorkbook : workbooks) {

					Set<TableauView> tableauViews = tableauWorkbook.getTableauViews();
					if(XoUtil.hasData(tableauViews)) {
						for(TableauView tableauView : tableauViews) {
							if(tableauView.isActive()){
								if(tableauView.getName().toLowerCase().startsWith("dashboard_")){
									screenDto.contentDtos.add(buildDashboardDto(
											DashboardItemEnum.PROJECT,
											tableauProject,
											tableauWorkbook,
											tableauView, applicationContext));
								}
							}
						}
					}
				}
			}
		}
		if(!XoUtil.hasData(screenDto.contentDtos) && !XoUtil.getApplicationEndUser().equalsIgnoreCase("democlient")){
			screenDto.placeHolderImageUrl = "vassets/images/Market_Map_Segments.jpg";
		}
	}

	public void loadDashboardGroupData(final ScreenDTO screenDto, XoClient xoClient) throws XODAOException, XOException {
		String applicationContext = XoUtil.getApplicationContext();
		final String tableauConfigJson = this.loadTableauConfig(xoClient);
		if(tableauConfigJson != null) {
			this.setTableauConfigs(tableauConfigJson);
			TableauSite tableauSite = this.tableauSiteDao.findByNameAndContentUrl(this.siteName, this.siteContentUrl);
			List<TableauProject> tableauProjects = new ArrayList<TableauProject>();
			if (tableauSite != null) {
				Set<TableauUser> tableauUsers = tableauSite.getTableauUsers();
				if (XoUtil.hasData(tableauUsers)) {
					for (TableauUser tableauUser : tableauUsers) {
						tableauProjects.addAll(tableauUser.getTableauProjects());
					}
				}
			}
			if (XoUtil.hasData(tableauProjects)) {
				for(TableauProject tableauProject : tableauProjects){
					Collection<ViewGroup> viewGroups = this.viewGroupDao.findAll();
					for (ViewGroup tableauViewGroup : viewGroups) {
						if(tableauViewGroup.isActive()){
							Set<TableauView> tableauViews = tableauViewGroup.getTableauViews();
							// Finding the main dashboard view and generating its links
							if(XoUtil.hasData(tableauViews)) {
								for(TableauView tableauView : tableauViews) {
									if(tableauView.isActive()) {
										if (tableauView.isDashboard() && tableauView.getName().toLowerCase().startsWith(MAIN_DASHBOARD_NAME)) {
											DashboardDTO dashboardDTO = buildDashboardDto(
													DashboardItemEnum.VIEW,
													tableauProject,
													tableauView.getTableauWorkbook(),
													tableauView, applicationContext);
											screenDto.contentDtos.add(dashboardDTO);
											screenDto.breadCrumbDtos = this.buildBreadCrumbsGroup(DashboardItemEnum.DASHBOARD, tableauProject, tableauViewGroup, tableauView);
											screenDto.imageUrl = dashboardDTO.imageUrl;
											screenDto.isMainDashboard = true;
											break;
										}
									}
								}
							}
						}
					}
					screenDto.menuDtos.addAll(buildGroupMenus());
				}
			}
		}

		if(!XoUtil.hasData(screenDto.contentDtos)){
			screenDto.placeHolderImageUrl = "vassets/images/Market_Map_Segments.jpg";
			screenDto.breadCrumbDtos = this.buildBreadCrumbsGroup(DashboardItemEnum.DASHBOARD, null, null, null);
		}
	}
	
	public void loadDiffusionData(final ScreenDTO screenDto, XoClient xoClient) {
		String applicationContext = XoUtil.getApplicationContext();
		DashboardDTO dashboardDTO =null;
		dashboardDTO = buildDiffusionDto("Production Test","Diffusion_map","DiffusionMap", applicationContext);
		screenDto.contentDtos.add(dashboardDTO);
		screenDto.imageUrl = dashboardDTO.imageUrl;
		if(!XoUtil.hasData(screenDto.contentDtos)){
			screenDto.placeHolderImageUrl = "vassets/images/Market_Map_Segments.jpg";
		}
	}
	
	public String getFilterList() {
		ResultSet filterList = this.tableauProjectDao.getFilterList();
		String filters = "";
		try {
			while(filterList.next()) {
				filters += filterList.getString("status");
				if(!filterList.isLast()) {
					filters+=",";
				}
			}
			return filters;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private DashboardDTO buildDiffusionDto(String tableauProject, String tableauWorkbook, String tableauView, String applicationContext) {
		DashboardDTO dashboardDTO = new DashboardDTO();
		
		dashboardDTO.name = tableauView;
		dashboardDTO.imageUrl = buildDiffusionLinkUrl(tableauWorkbook, tableauView);
		
		return dashboardDTO;
	}

	private String buildDiffusionLinkUrl(String tableauWorkbook, String tableauView) {
		String urlWorkbookName = tableauWorkbook.replaceAll(" ", "");
		String urlViewName = tableauView.replaceAll(" ", "");
		StringBuilder tableauImageurl = new StringBuilder();
		tableauImageurl.append(this.tableauPublicHost);
		tableauImageurl.append(TableauRESTConnector.URL_QUERY_TRUSTED_IP_TICKET);
		tableauImageurl.append(SYMPOL_FORWARD_SLASH);
		tableauImageurl.append(tableauConnector.getTicket(this.tableauInternalHost, /*this.userDomain + "\\" + */this.tableauUserName, this.clientIP, "Xo_Prod"));
		tableauImageurl.append(SYMPOL_FORWARD_SLASH);
		tableauImageurl.append(TABLEAU_MULTI_SITE);
		tableauImageurl.append(SYMPOL_FORWARD_SLASH);
		tableauImageurl.append("Xo_Prod");
		tableauImageurl.append(SYMPOL_FORWARD_SLASH);
		tableauImageurl.append("views");
		tableauImageurl.append(SYMPOL_FORWARD_SLASH);
		tableauImageurl.append(urlWorkbookName);
		tableauImageurl.append(SYMPOL_FORWARD_SLASH);
		tableauImageurl.append(urlViewName);
		tableauImageurl.append(TABLEAU_REPORT_URL_PARAMS);
		return tableauImageurl.toString();
	}

	public Set<MenuDTO> buildGroupMenus() {
		Set<MenuDTO> menuDtos = new HashSet<MenuDTO>();
		String applicationContext = XoUtil.getApplicationContext();

		if(XoUtil.isNotNull(this.siteName)) {

			TableauSiteDao tableauSiteDao = new TableauSiteDaoImpl();
			TableauSite tableauSite = tableauSiteDao.findByNameAndContentUrl(this.siteName, this.siteContentUrl);

			if(tableauSite != null && XoUtil.hasData(tableauSite.getTableauUsers())) {

				Set<TableauUser> tableauUsers = tableauSite.getTableauUsers();

				for(TableauUser tableauUser : tableauUsers) {

					Set<TableauProject> tableauProjects = tableauUser.getTableauProjects();
					List<ViewGroup> viewGroups = new ArrayList<ViewGroup>(this.viewGroupDao.findAll());

					if(XoUtil.hasData(tableauProjects) && XoUtil.hasData(viewGroups)) {

						Collections.sort((viewGroups), new DisplayOrderComparator());

						for(TableauProject tableauProject : tableauProjects) {

							MenuDTO tableauProjectMenuDto = this.buildMenu(DashboardItemEnum.DASHBOARD, tableauProject, null, null, applicationContext);
							menuDtos.add(tableauProjectMenuDto);

							// Building menus based on view groups
							for(ViewGroup tableauViewGroup : viewGroups) {

								if(tableauViewGroup.isActive()){

									Set<TableauView> tableauViews = tableauViewGroup.getTableauViews();

									if(XoUtil.hasData(tableauViews)) {

										MenuDTO tableauWorkbookMenuDto = this.buildGroupMenu(DashboardItemEnum.PROJECT, tableauProject, tableauViewGroup, null, applicationContext);
										tableauProjectMenuDto.subMenus.add(tableauWorkbookMenuDto);

										List<TableauView> orderedViewGroups = new ArrayList<TableauView>(tableauViews);
										Collections.sort((orderedViewGroups), new DisplayOrderComparator());

										for(TableauView tableauView : orderedViewGroups) {
											if(tableauView.isActive()){
												if(!tableauView.getName().toLowerCase().startsWith(MAIN_DASHBOARD_NAME)){

													MenuDTO tableauViewMenuDto = this.buildGroupMenu(DashboardItemEnum.VIEW, tableauProject, tableauViewGroup, tableauView, applicationContext);
													tableauWorkbookMenuDto.subMenus.add(tableauViewMenuDto);
												}
											}
										}
									}
								}

							}
						}
					}
				}
			}
		}
		return menuDtos;
	}

	public void syncTableauReports(final XoClient xoClient) throws Exception{
		try{
			this.tableauConnector.syncPortalData(this.loadTableauConfig(xoClient));
			this.verifyReportConfiguration(xoClient);
		} catch(Exception e) {
			throw e;
		}
	}

	public void verifyReportConfiguration(final XoClient xoClient) {
		ScreenDTO screenDTO = new ScreenDTO();
		try {
			this.loadDashboardGroupData(screenDTO, xoClient);
		} catch(Exception e) {
			Logger.error("Error while verifing the report menu configurations.", e);
		}
		try{
			if(!XoUtil.hasData(screenDTO.menuDtos)) {
				ConfigurationDto configurationDto = XOCONFIG_INSTANCE_LOGIC.findByName(XOAPP_SUPPORT_TEAM);
				JsonNode jsonNode = Json.parse(configurationDto.configJson);
				if(jsonNode != null) {
					JsonNode contactEmailsNode = jsonNode.findPath(CONTACT_EMAILS);
					List<String> supportTeamEmails = new ArrayList<String>();
					if(contactEmailsNode.isArray()) {
						for(JsonNode emailValueNode : contactEmailsNode) {
							supportTeamEmails.add(emailValueNode.asText());
						}
						this.sendReportsReconfigureMail(supportTeamEmails);
					}
				}
			}
		} catch(Exception error) {
			Logger.error("Error while verifing the report menu configurations.", error);
		}
	}

	private void sendReportsReconfigureMail(final List<String> emailIds) {
		try{
			if(XoUtil.hasData(emailIds)) {
				Set<MailDto> mailDtos = new HashSet<MailDto>();
				final String subject = this.getReconfigureReportEmailMailSubject();
				final Method emailTemplateRenderer = xoMailContentProvider.getEmailTemplateRenderer(RECONFIGURE_REPORTS_EMAIL_TEMPLATE);
				String body = null;
				for(String email : emailIds) {
					body = xoMailContentProvider.getEmailTemplate(emailTemplateRenderer);
					mailDtos.add(new MailDto(body, subject, email));
				}
				new XoMailSender(mailDtos);
			}
		} catch(XOException e) {
			Logger.error("Error while sending mail.", e);
		}
	}

	private String getReconfigureReportEmailMailSubject() {
		return Messages.get("reports.mgmt.email.config.subject");
	}

	public static final String latestTableauPublicHost() {
		return new com.xo.web.ext.tableau.mgr.TableauObjectLogic().tableauPublicHost;
	}
}