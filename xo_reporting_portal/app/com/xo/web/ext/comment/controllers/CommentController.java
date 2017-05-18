package com.xo.web.ext.comment.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.xo.web.controllers.BaseController;
import com.xo.web.core.XOException;
import com.xo.web.ext.comment.mgr.CommentLogic;
import com.xo.web.ext.comment.models.Comment;
import com.xo.web.ext.comment.viewdtos.CommentDto;

import play.Logger;
import play.i18n.Messages;
import play.libs.Json;
import play.mvc.Result;


public class CommentController extends BaseController<Comment, Integer> {

	private final CommentLogic commentLogic;
	public static final String MSG_SAVED_SUCCESSFULLY = "Success";
	public static final String ERR_SAVING_MESSAGE = "Error";
	public static final String ERR_UNABLE_TO_LOAD_ROOM = "Error";
	  
	  public CommentController() {
	    super(new CommentLogic());
	    this.commentLogic = (CommentLogic) this.entityLogic;
	  }
	  
	  public  Result postMessage() throws XOException {
		JsonNode json = request().body().asJson();
		JsonNode jsonResponse = null;
		if (json == null) {
			Logger.error(BAD_REQUEST_UNKNOWN_DATA);
			return badRequest(generateErrorResponse(BAD_REQUEST_UNKNOWN_DATA));
		}
		try {
			CommentDto commentDto = Json.fromJson(json, CommentDto.class);
			Comment comment =this.commentLogic.save(commentDto);
			CommentDto commentDtoContent=new CommentDto(comment.getMessageId(),comment.getMessage(),comment.getTs(),comment.getUser().getFirstName(), comment.getSheetName(),comment.getDashboardName() );
			jsonResponse = generateSuccessResponse(Messages.get(MSG_SAVED_SUCCESSFULLY),commentDtoContent);
			this.commentLogic.sendEmailToDev(comment);
		}catch(Exception e) {
			Logger.error("Error while udpating the User details.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_SAVING_MESSAGE));
			throw new XOException(e);
		}
		return ok(jsonResponse);
	}
	  
	public Result readAllComments() throws XOException {
		JsonNode jsonResponse = null;
		try {
			jsonResponse = Json.toJson(this.commentLogic.readAllComments());    
		} catch (Exception e) {
			jsonResponse = generateErrorResponse(Messages.get(ERR_UNABLE_TO_LOAD_ROOM));
			throw new XOException(e);
		}
		return ok(jsonResponse);
	}
	
	public Result readSheetComments(String sheetName, String dashboardName) throws XOException {
		JsonNode jsonResponse = null;
		try {
			jsonResponse = Json.toJson(this.commentLogic.readSheetComments(sheetName,dashboardName));    
		} catch (Exception e) {
			jsonResponse = generateErrorResponse(Messages.get(ERR_UNABLE_TO_LOAD_ROOM));
			throw new XOException(e);
		}
		return ok(jsonResponse);
	}
	  
}
