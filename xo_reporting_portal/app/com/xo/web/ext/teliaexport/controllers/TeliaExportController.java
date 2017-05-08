package com.xo.web.ext.teliaexport.controllers;

import com.xo.web.controllers.BaseController;
import com.xo.web.core.XODAOException;
import com.xo.web.core.XOException;
import com.xo.web.ext.teliaexport.mgr.TeliaExportLogic;
import com.xo.web.ext.teliaexport.models.TeliaExport;
import com.xo.web.ext.teliaexport.viewdtos.TeliaExportDto;

import play.Logger;
import play.i18n.Messages;
import play.libs.EventSource;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Result;

import com.fasterxml.jackson.databind.JsonNode;


public class TeliaExportController extends BaseController<TeliaExport, Integer> {

	private final TeliaExportLogic teliaExportLogic;
	public static final String MSG_SAVED_SUCCESSFULLY = "Success";
	public static final String ERR_SAVING_MESSAGE = "Error";
	public static final String ERR_UNABLE_TO_LOAD_ROOM = "Error";
	  
	  public TeliaExportController() {
	    super(new TeliaExportLogic());
	    this.teliaExportLogic = (TeliaExportLogic) this.entityLogic;
	  }
	  
	  public  Result exportData() {
		JsonNode json = request().body().asJson();
		JsonNode jsonResponse = null;
		if (json == null) {
			Logger.error(BAD_REQUEST_UNKNOWN_DATA);
			return badRequest(generateErrorResponse(BAD_REQUEST_UNKNOWN_DATA));
		}
		try {
			TeliaExportDto TeliaExportDto = Json.fromJson(json, TeliaExportDto.class);
			TeliaExport TeliaExport =this.teliaExportLogic.save(TeliaExportDto);
			TeliaExportDto TeliaExportDtoContent=new TeliaExportDto(TeliaExport.getExportId(),TeliaExport.getCreatedDate(),TeliaExport.getUser().getFirstName(), TeliaExport.getDateOfEvent(),TeliaExport.getnoOfUsers() );
			jsonResponse = generateSuccessResponse(Messages.get(MSG_SAVED_SUCCESSFULLY),TeliaExportDtoContent);
		}catch(Exception e) {
			Logger.error("Error while udpating the User details.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_SAVING_MESSAGE));
			throw new XOException(e);
		} finally{			
			return ok(jsonResponse);
		}
	}
	
	  
}
