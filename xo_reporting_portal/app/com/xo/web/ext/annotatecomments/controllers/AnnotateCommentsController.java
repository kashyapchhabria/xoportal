package com.xo.web.ext.annotatecomments.controllers;

import com.xo.web.controllers.BaseController;
import com.xo.web.core.XODAOException;
import com.xo.web.core.XOException;
import com.xo.web.ext.annotatecomments.mgr.AnnotateCommentsLogic;
import com.xo.web.ext.annotatecomments.models.AnnotateComments;
import com.xo.web.ext.annotatecomments.viewdtos.AnnotateCommentsDto;

import play.Logger;
import play.i18n.Messages;
import play.libs.EventSource;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Result;

import com.fasterxml.jackson.databind.JsonNode;


public class AnnotateCommentsController extends BaseController<AnnotateComments, Integer> {

	private final AnnotateCommentsLogic annotateCommentsLogic;
	public static final String MSG_SAVED_SUCCESSFULLY = "Success";
	public static final String ERR_SAVING_MESSAGE = "Error";
	public static final String ERR_UNABLE_TO_LOAD_ROOM = "Error";
	  
	  public AnnotateCommentsController() {
	    super(new AnnotateCommentsLogic());
	    this.annotateCommentsLogic = (AnnotateCommentsLogic) this.entityLogic;
	  }
	  
	  public  Result exportData() {
		JsonNode json = request().body().asJson();
		JsonNode jsonResponse = null;
		if (json == null) {
			Logger.error(BAD_REQUEST_UNKNOWN_DATA);
			return badRequest(generateErrorResponse(BAD_REQUEST_UNKNOWN_DATA));
		}
		try {
			AnnotateCommentsDto AnnotateCommentsDto = Json.fromJson(json, AnnotateCommentsDto.class);
			AnnotateComments AnnotateComments =this.annotateCommentsLogic.save(AnnotateCommentsDto);
			AnnotateCommentsDto AnnotateCommentsDtoContent=new AnnotateCommentsDto(AnnotateComments.getAnnotateId(),AnnotateComments.getUser().getFirstName(), AnnotateComments.getReportName(),AnnotateComments.getFieldName1(),AnnotateComments.getFieldName2(),AnnotateComments.getWorkbookName(),AnnotateComments.getStatus(),AnnotateComments.getComment());
			jsonResponse = generateSuccessResponse(Messages.get(MSG_SAVED_SUCCESSFULLY),AnnotateCommentsDtoContent);
		}catch(Exception e) {
			Logger.error("Error while udpating the User details.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_SAVING_MESSAGE));
			throw new XOException(e);
		} finally{			
			return ok(jsonResponse);
		}
	}
	
	  public Result updateComments(String fieldName1, String comment, String user) {
			JsonNode jsonResponse = null;
			try {
				annotateCommentsLogic.updateComment(fieldName1, comment, user); 
				jsonResponse = generateSuccessResponse("messagesavesuccessfully");
			} catch (Exception e) {
				jsonResponse = generateErrorResponse(Messages.get(ERR_UNABLE_TO_LOAD_ROOM));
				throw new XOException(e);
			} finally{
				return ok(jsonResponse);
			}
	  }
	  
	  public Result getFieldComment(String fieldName1) {
			JsonNode jsonResponse = null;
			try {
				jsonResponse = Json.toJson(this.annotateCommentsLogic.getFieldComment(fieldName1));    
			} catch (Exception e) {
				jsonResponse = generateErrorResponse(Messages.get(ERR_UNABLE_TO_LOAD_ROOM));
				throw new XOException(e);
			} finally{
				return ok(jsonResponse);
			}
		}
}
