package com.xo.web.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.xo.web.core.XOException;
import com.xo.web.mgr.XoClientLogic;
import com.xo.web.models.system.PermissionEnum;
import com.xo.web.models.system.XoClient;
import com.xo.web.security.authorization.action.Authroize;

import play.Logger;
import play.i18n.Messages;
import play.libs.Json;
import play.mvc.Result;

@SuppressWarnings("finally")
public class XoClientController extends BaseController<XoClient, Integer> implements XoClientI18NLabels {

	private final XoClientLogic xoClientLogic;

	public XoClientController() {
		super(new XoClientLogic());
		this.xoClientLogic = (XoClientLogic) entityLogic;
	}

	@Authroize(permissions = {PermissionEnum.READ_CLIENT})
	public Result readAll() {
		JsonNode jsonResponse = null;
		try {
			jsonResponse = Json.toJson(this.xoClientLogic.findAllClients());
		} catch (Exception e) {
			Logger.error("Error while reading the role list.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_UNABLE_TO_LOAD_CLIENTS));
			throw new XOException(e);
		} finally{
			return ok(jsonResponse);
		}
	}
}
