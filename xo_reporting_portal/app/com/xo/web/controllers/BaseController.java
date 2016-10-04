package com.xo.web.controllers;

import java.io.Serializable;

import play.Logger;
import play.libs.Json;
import play.mvc.Result;

import com.fasterxml.jackson.databind.JsonNode;
import com.xo.web.core.XOException;
import com.xo.web.mgr.BaseLogic;
import com.xo.web.models.system.IEntity;
import com.xo.web.persistence.DAOUtil;

@SuppressWarnings("unchecked")
public class BaseController<T extends IEntity, ID extends Serializable> extends XOBaseController {

    protected Class<T> operatingClass = (Class<T>) DAOUtil.getTypeArguments(BaseController.class, this.getClass()).get(0);
    protected final String simpleClzzName; 
    protected BaseLogic<T, ID> entityLogic;

	public BaseController(BaseLogic<T, ID> entityLogic) {
		this.entityLogic = entityLogic;
		this.simpleClzzName = this.operatingClass.getSimpleName();
	}

	public Result create() {
		JsonNode jsonResponse = generateSuccessResponse(this.simpleClzzName + " created successfully.");
		return ok(jsonResponse);
	}

	public Result read(ID id) {
		JsonNode jsonResponse = generateSuccessResponse(this.simpleClzzName + " read successfully.");
		return ok(jsonResponse);
	}

	public Result update(ID id) {
		JsonNode jsonResponse = generateSuccessResponse(this.simpleClzzName + " update successfully.");
		return ok(jsonResponse);
	}

	public Result delete(ID id) {
		JsonNode jsonResponse = null;
		try{
			this.entityLogic.softRemove(id);
			jsonResponse = generateSuccessResponse(this.simpleClzzName + " deleted successfully.");
		} catch(Exception e) {
			Logger.error("Error while deleting the entity", e);
			jsonResponse = generateSuccessResponse("Error while deleting the " + this.simpleClzzName);
		}
		return ok(jsonResponse);
	}

	public Result readAll() {
		JsonNode jsonResponse = generateSuccessResponse(this.simpleClzzName + " read successfully.");
		return ok(jsonResponse);
	}

	@SuppressWarnings("finally")
	public Result readAllAsKeyValue() {
		JsonNode jsonResponse = null;
		try {
			jsonResponse = Json.toJson(this.entityLogic.readAllAsKeyValues());
		} catch (Exception e) {
			Logger.error("Error while reading the " + this.simpleClzzName + " list.", e);
			jsonResponse = generateErrorResponse(this.simpleClzzName + " read successfully.");
			throw new XOException(e);
		} finally{
			return ok(jsonResponse);
		}
	}
}
