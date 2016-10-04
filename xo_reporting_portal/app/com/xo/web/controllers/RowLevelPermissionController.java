package com.xo.web.controllers;

import java.io.Serializable;

import play.Logger;
import play.i18n.Messages;
import play.libs.Json;
import play.mvc.Result;

import com.fasterxml.jackson.databind.JsonNode;
import com.xo.web.core.XOException;
import com.xo.web.mgr.RowLevelPermissionLogic;
import com.xo.web.models.system.IEntity;

public class RowLevelPermissionController<T extends IEntity, ID extends Serializable> extends
		BaseController<T, ID> implements RowLevelPermissionI18NLabels {

	private final RowLevelPermissionLogic<T, ID> rowLevelPermissionLogic; 

	public RowLevelPermissionController(RowLevelPermissionLogic<T, ID> rowLevelPermissionLogic) {
		super(rowLevelPermissionLogic);
		this.rowLevelPermissionLogic = (RowLevelPermissionLogic<T, ID>) this.entityLogic;
	}

	@SuppressWarnings("finally")
	public Result readAll() {
		JsonNode jsonResponse = null;
		try {
			jsonResponse = Json.toJson(this.rowLevelPermissionLogic.findAllRecords());
		} catch (Exception e) {
			Logger.error("Error while reading the users list.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_UNABLE_TO_LOAD_RLP_INSTANCES));
			throw new XOException(e);
		} finally{			
			return ok(jsonResponse);
		}
	}

	@SuppressWarnings("finally")
	public Result readAllByRlpAndResourType(Integer rlpTypeId, Integer resourceTypeId) {
		JsonNode jsonResponse = null;
		try {
			jsonResponse = Json.toJson(this.rowLevelPermissionLogic.findAllByRlpTypeIdAndResourceType(rlpTypeId, resourceTypeId));
		} catch (Exception e) {
			Logger.error("Error while reading the users list.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_UNABLE_TO_LOAD_RLP_INSTANCES));
			throw new XOException(e);
		} finally{			
			return ok(jsonResponse);
		}
	}

	@SuppressWarnings("finally")
	public Result readAllEntityInstances(Integer rlpTypeId, Integer resourceTypeId) {
		JsonNode jsonResponse = null;
		try {
			jsonResponse = Json.toJson(this.rowLevelPermissionLogic.findAllEntityInstances(rlpTypeId, resourceTypeId));
		} catch (Exception e) {
			Logger.error("Error while reading the users list.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_UNABLE_TO_LOAD_RLP_INSTANCES));
			throw new XOException(e);
		} finally{			
			return ok(jsonResponse);
		}
	}
}
