package com.xo.web.controllers;

import java.io.FileWriter;

import java.net.InetAddress;
import java.util.Set;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import play.i18n.Messages;
import play.mvc.Result;
import play.Logger;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.xo.web.ext.tableau.mgr.DashboardItemEnum;
import com.xo.web.ext.tableau.mgr.TableauObjectLogic;
import com.xo.web.ext.tableau.mgr.TableauProjectLogic;
import com.xo.web.ext.tableau.mgr.TableauSiteLogic;
import com.xo.web.ext.tableau.mgr.TableauViewLogic;
import com.xo.web.ext.tableau.mgr.TableauWorkbookLogic;
import com.xo.web.ext.tableau.models.TableauSite;
import com.xo.web.ext.tableau.models.TableauView;
import com.xo.web.ext.tableau.models.TableauWorkbook;
import com.xo.web.mgr.XoClientLogic;
import com.xo.web.models.system.PermissionEnum;
import com.xo.web.models.system.User;
import com.xo.web.models.system.XoClient;
import com.xo.web.security.authorization.action.Authroize;
import com.xo.web.util.XoAppConstant;
import com.xo.web.util.XoUtil;
import com.xo.web.viewdtos.DashboardDTO;
import com.xo.web.viewdtos.ScreenDTO;

public class DashboardController extends BaseController<TableauSite, String> {

	private final TableauObjectLogic tableauObjectLogic = new TableauObjectLogic();
	private final TableauProjectLogic tableauProjectLogic = new TableauProjectLogic();
	private final TableauWorkbookLogic tableauWorkbookLogic = new TableauWorkbookLogic();
	private final TableauViewLogic tableauViewLogic = new TableauViewLogic();
	private final XoClientLogic xoClientLogic = new XoClientLogic();
	private static final String index = "kashyap"; 
	private static final String type = "CampaignManagement"; 

	public DashboardController() {
		super(new TableauSiteLogic());
	}

	@Authroize(permissions = {PermissionEnum.READ_TABLEAU_PROJECT,PermissionEnum.READ_TABLEAU_WORKBOOK,PermissionEnum.READ_TABLEAU_VIEW}, meta = "Tableau CORS")
	public Result renderDashboard(){
		return ok(com.xo.web.views.html.dashboard_projects.render());
	}

	@Authroize(permissions = {PermissionEnum.READ_TABLEAU_PROJECT,PermissionEnum.READ_TABLEAU_WORKBOOK,PermissionEnum.READ_TABLEAU_VIEW}, meta = "Tableau CORS")
	public Result loadDashboardGroupData(){
		ScreenDTO screenDTO = new ScreenDTO();
		try {
			User user = this.getCurrentRestUser();
			XoClient xoClient = null;
			if(user.isSuperUser() || this.getCurrentRestUser().isSystemResource()) {
				xoClient = xoClientLogic.find(Integer.parseInt(request().getHeader(XoAppConstant.HEADER_X_SUPER_CLIENT)));
			} else {
				xoClient = user.getXoClient();
			}
			tableauObjectLogic.loadDashboardGroupData(screenDTO, xoClient);
		} catch(Exception e) {
			screenDTO.errorText = Messages.get("tableau.report.noreports");
		}
		return ok(screenDTO.toJson());
	}

	@Authroize(permissions = {PermissionEnum.READ_TABLEAU_PROJECT,PermissionEnum.READ_TABLEAU_WORKBOOK, PermissionEnum.READ_TABLEAU_VIEW}, meta = "Tableau CORS")
	public Result loadProjectDashboardData(String projectId) {
		ScreenDTO screenDTO = new ScreenDTO();
		screenDTO.breadCrumbDtos = tableauObjectLogic.buildBreadCrumbsGroup(DashboardItemEnum.PROJECT, tableauProjectLogic.find(projectId), null, null);
		tableauObjectLogic.loadProjectDashboard(projectId, screenDTO);
		screenDTO.breadCrumbDtos = Lists.reverse(screenDTO.breadCrumbDtos);
		return ok(screenDTO.toJson());
	}

	@Authroize(permissions = {PermissionEnum.READ_TABLEAU_PROJECT,PermissionEnum.READ_TABLEAU_WORKBOOK,PermissionEnum.READ_TABLEAU_VIEW}, meta = "Tableau CORS")
	public Result loadViewData(String workbookId, String viewId) {
		ScreenDTO screenDTO = new ScreenDTO();
		try {
			String applicationContext = XoUtil.getApplicationContext();
			TableauWorkbook tableauWorkbook = tableauWorkbookLogic.find(workbookId);
			TableauView selectedView = tableauViewLogic.find(viewId);
			if(tableauWorkbook != null) {

				screenDTO.breadCrumbDtos = tableauObjectLogic.buildBreadCrumbsGroup(DashboardItemEnum.VIEW,
						tableauWorkbook.getTableauProject(), selectedView.getViewGroup(), selectedView);
				Set<TableauView> tableauViews = tableauWorkbook.getTableauViews();
				DashboardDTO dashboardDto = null;
				if(XoUtil.hasData(tableauViews)) {
					boolean isNotGot = true;
					for(TableauView tableauView : tableauViews) {
						if(isNotGot) {
							dashboardDto = tableauObjectLogic.buildDashboardDto(DashboardItemEnum.VIEW,	null, tableauWorkbook,
									tableauView, applicationContext);
							isNotGot = false;
						}
						screenDTO.menuDtos.add(tableauObjectLogic.buildMenu(DashboardItemEnum.VIEW,	null, tableauWorkbook,
								tableauView, applicationContext));
					}
				}
				if(XoUtil.isNotNull(viewId)){
					dashboardDto = tableauObjectLogic.buildDashboardDto(DashboardItemEnum.VIEW,	null, tableauWorkbook, selectedView, applicationContext);
				}
				if(dashboardDto != null && XoUtil.isNotNull(dashboardDto.imageUrl)) {
					screenDTO.imageUrl =  dashboardDto.imageUrl;
				}
			}
			screenDTO.breadCrumbDtos = Lists.reverse(screenDTO.breadCrumbDtos);
		} catch(Exception e) {
			screenDTO.errorText = Messages.get("tableau.report.reportloaderr");
		}
		return ok(screenDTO.toJson());
	}
	
	public Result getMsisdns() {
		JsonNode json = request().body().asJson();
		JsonNode jsonResponse = null;
		Client client = null; 
		SearchResponse response;
		if (json == null) {
			Logger.error(BAD_REQUEST_UNKNOWN_DATA);
			return badRequest(generateErrorResponse(BAD_REQUEST_UNKNOWN_DATA));
		}
		
		try {
			
			Settings settings = Settings.settingsBuilder()
            		.put("client.transport.ignore_cluster_name", true) 
                    .put("client.transport.sniff", true) 
                    .build(); 
    		        
            client = TransportClient.builder().settings(settings).build()
    		        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
           // String name = json.rootNode("segment").getTextValue();
            ObjectMapper mapper = new ObjectMapper(); 
            //String text = mapper.writeValueAsString(json);
           // String text = json.path("segment").asText();
            //System.out.println(text);
            //String pretty = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json); 
            
            
            

           // String text =json.path("regions").toString();
            //System.out.println(text);
            BoolQueryBuilder qb = QueryBuilders.boolQuery()
        		    .must(QueryBuilders.matchQuery("segment.topSegment",json.path("segment").asText() ))
        		    .must(QueryBuilders.matchQuery("demographics.homeLocation",json.path("regions").toString()));
        		    
        		
            response = client.prepareSearch(index) 
            		.setTypes(type)
            		.setQuery(qb)
            		.addFields("msisdn" , "demographics.homeLocation" , "segment.topSegment")
            		.setScroll(new TimeValue(60000))
            		.setSize(100)
            		.execute() 
                    .actionGet();
            jsonResponse = generateSuccessResponse(response.toString());
            System.out.println(response.toString());
		} catch (Exception e) { 
            System.out.println("00000 " + e); 
        } finally { 
            if (client != null) { 
                client.close(); 
            } 
        }
		return ok(jsonResponse);
	}
}
