package com.xo.web.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.xo.web.core.RythmTemplateLoader;
import com.xo.web.mgr.TokenActionLogic;
import com.xo.web.models.system.User;
import com.xo.web.util.XoUtil;
import com.xo.web.viewdtos.BaseDto;
import com.xo.web.viewdtos.MessageDto;
import com.xo.web.viewdtos.MessageType;
import play.Logger;
import play.mvc.Controller;
import play.mvc.Http.Context;
import play.mvc.Result;

import java.text.SimpleDateFormat;
import java.util.Date;

//@ContentSecurityPolicy
public class XOBaseController extends Controller {

	private static final String MSG_APP_HEART_BEAT = "I am alive.";
	protected static final String FALLBACK_LANGUAGE = "en";
	public static final String BAD_REQUEST_UNKNOWN_DATA = "JSON data expected.";
	protected static final String ROLE_NAME = "roleName";
	protected static final String USER_EMAIL = "email";
	protected final TokenActionLogic TOKEN_ACTION_LOGIC = new TokenActionLogic();
	private final RythmTemplateLoader rtl;

	public XOBaseController(){
		this.rtl = RythmTemplateLoader.getTemplateLoader();
	}

	public Result accessDenied() {
		return forbidden(com.xo.web.views.html.access_denied.render());
	}

	public User getCurrentRestUser() {
		String authToken = Context.current().request().getHeader(XoUtil.HEADER_AUTH_TOKEN);
		return TOKEN_ACTION_LOGIC.getRestConnectedUser(authToken);
	}

	public static String formatTimestamp(final long t) {
		return new SimpleDateFormat("yyyy-dd-MM HH:mm:ss").format(new Date(t));
	}

	public Result cspReportParser() {
	    JsonNode json = request().body().asJson();
	    if(json == null) {
	        Logger.debug("no JSON payload");
	        return badRequest("Expecting Json data");
	    } else {
	        Logger.debug(json.toString());
	        return ok();
	    }
	}

	public JsonNode generateErrorResponse(String message, Throwable...excepError) {
		
		StringBuilder errorMsg = new StringBuilder(message);
		/*for(Throwable errorObject : excepError) {
			StringWriter errorString = new StringWriter();
			errorObject.printStackTrace(new PrintWriter(errorString));
			errorMsg.append("\n");
			errorMsg.append(errorString.toString());
		}*/
		return generateMessage(errorMsg.toString(), MessageType.alert);
	}

	public JsonNode generateErrorResponse(String message) {
		return generateMessage(message, MessageType.alert);
	}

	public JsonNode generateErrorResponse(String message, BaseDto<? extends BaseDto<?>> resultObject) {
		return generateMessage(message, MessageType.alert, resultObject);
	}
	
	public JsonNode generateAlertResponse(String message) {
		return generateMessage(message, MessageType.alert);
	}

	public JsonNode generateWarningResponse(String message) {
		return generateMessage(message, MessageType.warning);
	}

	public JsonNode generateSuccessResponse(String message) {
		return generateMessage(message, MessageType.success);
	}

	public JsonNode generateSuccessResponse(String message, BaseDto<? extends BaseDto<?>> resultObject) {
		return generateMessage(message, MessageType.success, resultObject);
	}
	
	public JsonNode generateInfoResponse(String message) {
		return generateMessage(message, MessageType.info);
	}

	public JsonNode generateMessage(String message, MessageType messageType) {
		MessageDto messageDto = new MessageDto(message, messageType);
		return messageDto.toJson();
	}

	public JsonNode generateMessage(String message, MessageType messageType, BaseDto<? extends BaseDto<?>> resultObject) {
		MessageDto messageDto = new MessageDto(message, messageType, resultObject);
		return messageDto.toJson();
	}

	public Result heartBeat() {
		return ok(MSG_APP_HEART_BEAT);
	}

	public String render(String templatePath, Object...arguments) {
		return this.rtl.render(templatePath, arguments);
	}
}
