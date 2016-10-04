package com.xo.web.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.xo.web.core.XOException;
import com.xo.web.mgr.XoJobLogic;
import com.xo.web.models.system.XoJob;
import play.Logger;
import play.libs.Json;
import play.mvc.Result;

public class XoJobController extends BaseController<XoJob, Integer> {

	private final XoJobLogic xoJobLogic;

	public XoJobController() {
		super(new XoJobLogic());
		this.xoJobLogic = (XoJobLogic) this.entityLogic;
	}

	public Result readAll() {
		JsonNode jsonResponse = null;
		try {
			jsonResponse = Json.toJson(this.xoJobLogic.readAll());
		} catch (Exception e) {
			Logger.error("Error while reading the job list.", e);
			//jsonResponse = generateErrorResponse(Messages.get(ERR_UNABLE_TO_LOAD_CONFIGURATIONS));
			throw new XOException(e);
		} finally{
			return ok(jsonResponse);
		}
	}
}
