package com.xo.web.ext.diffusionmapexport.controllers;

import com.xo.web.controllers.BaseController;
import com.xo.web.core.XODAOException;
import com.xo.web.core.XOException;
import com.xo.web.ext.diffusionmapexport.mgr.DiffusionMapExportLogic;
import com.xo.web.ext.diffusionmapexport.models.DiffusionMapExport;
import com.xo.web.ext.diffusionmapexport.viewdtos.DiffusionMapExportDto;
import com.xo.web.models.system.PermissionEnum;
import com.xo.web.security.authorization.action.Authroize;
import com.xo.web.util.XoUtil;

import play.Logger;
import play.Play;
import play.i18n.Messages;
import play.libs.EventSource;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Result;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.databind.JsonNode;


public class DiffusionMapExportController extends BaseController<DiffusionMapExport, Integer> {

	private final DiffusionMapExportLogic diffusionMapExportLogic;
	public static final String MSG_SAVED_SUCCESSFULLY = "Success";
	public static final String ERR_SAVING_MESSAGE = "Error";
	public static final String ERR_UNABLE_TO_LOAD_ROOM = "Error";
	private static final String CONTENT_DISPOSITION = "Content-disposition";
	private static final String DOWNLOAD_FILE_CONTENT = "attachment; filename=";
	private static final String FILE_TYPE = ".csv";
	
	public DiffusionMapExportController() {
		super(new DiffusionMapExportLogic());
		this.diffusionMapExportLogic = (DiffusionMapExportLogic) this.entityLogic;
	}
	  
	public  Result exportData() {
		JsonNode json = request().body().asJson();
		JsonNode jsonResponse = null;
		if (json == null) {
			Logger.error(BAD_REQUEST_UNKNOWN_DATA);
			return badRequest(generateErrorResponse(BAD_REQUEST_UNKNOWN_DATA));
		}
		try {
			DiffusionMapExportDto DiffusionMapExportDto = Json.fromJson(json, DiffusionMapExportDto.class);
			DiffusionMapExport DiffusionMapExport =this.diffusionMapExportLogic.save(DiffusionMapExportDto);
			DiffusionMapExportDto DiffusionMapExportDtoContent=new DiffusionMapExportDto(DiffusionMapExport.getExportId(),DiffusionMapExport.getCreatedDate(),DiffusionMapExport.getUser().getFirstName(), DiffusionMapExport.getyTile(),DiffusionMapExport.getxTile(),DiffusionMapExport.getLocationType(),DiffusionMapExport.getRegion(),DiffusionMapExport.getStatus(),DiffusionMapExport.getSubsegmentLabel(),DiffusionMapExport.getDeviceDate(),DiffusionMapExport.getSpendSegment(),DiffusionMapExport.getLifetimeBucket());
			jsonResponse = generateSuccessResponse(Messages.get(MSG_SAVED_SUCCESSFULLY),DiffusionMapExportDtoContent);
		}catch(Exception e) {
			Logger.error("Error while udpating the User details.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_SAVING_MESSAGE));
			throw new XOException(e);
		} finally{			
			return ok(jsonResponse);
		}
	}
	
	@Authroize(permissions = {PermissionEnum.CREATE_TARGET})
	public Result getMsisdns() {
		JsonNode json = request().body().asJson();
		JsonNode jsonResponse = null;
		
		if (json == null) {
			Logger.error(BAD_REQUEST_UNKNOWN_DATA);
			return badRequest(generateErrorResponse(BAD_REQUEST_UNKNOWN_DATA));
		}

		try {
			if (XoUtil.jsonValidate(json, "segment")) { 
				DiffusionMapExportDto filterDto = Json.fromJson(json, DiffusionMapExportDto.class);
				String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
				String fileName = "msisdn" + date + FILE_TYPE;
				//String file = "msisdn" + '_' + date + FILE_TYPE;
				//System.out.println(file);
				File campaignFile = new File(fileName);
				campaignFile.createNewFile();
				FileWriter writer = new FileWriter(campaignFile);
				writer = diffusionMapExportLogic.getMsisdns(filterDto, writer);
				writer.flush();
				writer.close();
				//return ok(campaignFile.getName());
				System.out.println(fileName);
				response().setContentType("application/x-download");
				response().setHeader(CONTENT_DISPOSITION,DOWNLOAD_FILE_CONTENT+ fileName);
				return ok(Play.application().getFile(fileName));
			} 
		} catch (XODAOException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ok("Error");
	}
	
	@Authroize(permissions = {PermissionEnum.CREATE_TARGET})
	public Result getMsisdnCount() throws IOException {
		JsonNode json = request().body().asJson();
		JsonNode jsonResponse = null;
		
		if (json == null) {
			Logger.error(BAD_REQUEST_UNKNOWN_DATA);
			return badRequest(generateErrorResponse(BAD_REQUEST_UNKNOWN_DATA));
		}
		try {
				DiffusionMapExportDto filterDto = Json.fromJson(json, DiffusionMapExportDto.class);
				jsonResponse = generateSuccessResponse(Messages.get(diffusionMapExportLogic.getMsisdnCount(filterDto)));
			
		} catch (XODAOException e) {
			e.printStackTrace();
		} 
		return ok(jsonResponse);
	}
	
	public Result getCsvFile(String fileName) {
//		response().setContentType("text/csv");
		response().setContentType("application/x-download");
		response().setHeader(CONTENT_DISPOSITION,DOWNLOAD_FILE_CONTENT+ fileName);
		return ok(Play.application().getFile(fileName));
	}
}
