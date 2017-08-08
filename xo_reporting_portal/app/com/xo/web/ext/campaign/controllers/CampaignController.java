package com.xo.web.ext.campaign.controllers;

import java.io.File;
import java.io.FileWriter;
import java.sql.Timestamp;

import com.fasterxml.jackson.databind.JsonNode;
import com.xo.web.controllers.BaseController;
import com.xo.web.ext.campaign.mgr.CampaignLogic;
import com.xo.web.ext.campaign.models.Campaign;
import com.xo.web.ext.campaign.viewdtos.CampaignDto;
import com.xo.web.util.XoAppConfigKeys;
import com.xo.web.util.XoUtil;

import play.Logger;
import play.i18n.Messages;
import play.libs.Json;
import play.mvc.Result;

public class CampaignController extends BaseController<Campaign, Integer> implements CampaignI18NLabels {

	private final CampaignLogic campaignLogic;
	private static final String	ERROR_WHILE_DOWNLOADING="Error downloading file";
	private static final String CONTENT_DISPOSITION = "Content-disposition";
	private static final String DOWNLOAD_FILE_CONTENT = "attachment; filename=";
	private static final String FILE_TYPE = ".csv";
	private static final String ENV_TYPE = XoUtil.getConfig(XoAppConfigKeys.ENV_TYPE);
	
	public CampaignController() {
		super(new CampaignLogic());
		this.campaignLogic = (CampaignLogic) this.entityLogic;
	}
	
	public Result saveCampaign() {
		JsonNode json = request().body().asJson();
		JsonNode jsonResponse = null;
		if (json == null) {
			Logger.error(BAD_REQUEST_UNKNOWN_DATA);
			return badRequest(generateErrorResponse(BAD_REQUEST_UNKNOWN_DATA));
		}
		try {
			CampaignDto campaignDto = Json.fromJson(json, CampaignDto.class);
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			String fileName = campaignDto.name + '_' + Long.toString(timestamp.getTime()) + FILE_TYPE;
			File campaignFile = new File(fileName);
			FileWriter writer = new FileWriter(campaignFile);
			writer = this.campaignLogic.getMsisdnsAsList(campaignDto, writer,json.get("setAb").asInt());
			writer.flush();
			writer.close();
			Campaign campaign = this.campaignLogic.save(campaignDto);
			System.out.println(campaignFile.getAbsolutePath());
			return ok(campaignFile.getName());
		} catch(Exception e) {
			Logger.error("Error while saving the campaign.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_SAVE_CAMPAIGN));
		}
		return ok(jsonResponse);
	}
	
	public Result getMsisdnCount() {
		JsonNode json = request().body().asJson();
		JsonNode jsonResponse = null;
		
		if (json == null) {
			Logger.error(BAD_REQUEST_UNKNOWN_DATA);
			return badRequest(generateErrorResponse(BAD_REQUEST_UNKNOWN_DATA));
		}
		try {
			CampaignDto campaignDto = Json.fromJson(json, CampaignDto.class);
			jsonResponse = generateSuccessResponse(Messages.get(this.campaignLogic.getMsisdnCount(campaignDto)));
		} catch (Exception e) {
			Logger.error("Unable to fetch count",e);
		} 
		return ok(jsonResponse);
	}
	
	public Result getTotalCount() {
		
		JsonNode jsonResponse = null;
		
		try {
			Long totalCount = this.campaignLogic.getTotalCount();
			jsonResponse = generateSuccessResponse(Messages.get(totalCount.toString()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ok(jsonResponse);
	}
	
	public Result getCsvFile(String fileName) {
		response().setContentType("application/x-download");
		response().setHeader(CONTENT_DISPOSITION,DOWNLOAD_FILE_CONTENT+fileName);
		String pwd = System.getProperty("user.dir");
		String filePath;
		if ( ENV_TYPE.equalsIgnoreCase("local")) {
			filePath = pwd + File.separatorChar +fileName;
		} else {
			filePath = pwd + File.separatorChar + ".." + File.separatorChar +fileName;
		}
		File fileToDownload = new File(filePath);
		if(fileToDownload.exists()) {
			return ok(fileToDownload);
		}
		return ok(ERROR_WHILE_DOWNLOADING);
	}

}
