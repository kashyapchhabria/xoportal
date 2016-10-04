package com.xo.web.controllers;

import play.Logger;
import play.i18n.Messages;
import play.libs.Json;
import play.mvc.Result;

import com.fasterxml.jackson.databind.JsonNode;
import com.xo.web.core.XOException;
import com.xo.web.mgr.RolePermissionResourceInstanceLogic;
import com.xo.web.models.system.RolePermissionResourceInstance;
import com.xo.web.models.system.RolePermissionResourceInstanceId;
import com.xo.web.util.XoUtil;
import com.xo.web.viewdtos.RowLevelPermissionDto;

@SuppressWarnings("finally")
public class RolePermissionResourceInstanceController extends 
				RowLevelPermissionController<RolePermissionResourceInstance, RolePermissionResourceInstanceId> {

	private final RolePermissionResourceInstanceLogic rolePermissionResourceInstanceLogic; 

	public RolePermissionResourceInstanceController() {
		super(new RolePermissionResourceInstanceLogic());
		this.rolePermissionResourceInstanceLogic = (RolePermissionResourceInstanceLogic) this.entityLogic;
	}

	public Result readAll() {
		return super.readAll();
	}

	public Result readAllByRoleAndResourType(Integer roleId, Integer resourceTypeId) {
		return super.readAllByRlpAndResourType(roleId, resourceTypeId);
	}

	public Result readAllEntityInstances(Integer roleId, Integer resourceTypeId) {
		return super.readAllEntityInstances(roleId, resourceTypeId);
	}

	public Result create() {
		JsonNode jsonResponse = null;
		try {
			JsonNode json = request().body().asJson();
			RowLevelPermissionDto rowLevelPermissionDto = Json.fromJson(json, RowLevelPermissionDto.class);
			this.rolePermissionResourceInstanceLogic.save(rowLevelPermissionDto);
			jsonResponse = generateSuccessResponse(Messages.get(MSG_RLP_ADDED_SUCCESSFULLY));
		} catch (Exception e) {
			Logger.error("Error while reading the RLP list.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_RLP_UNABLE_GRANT_PERMISSION));
			throw new XOException(e);
		} finally{			
			return ok(jsonResponse);
		}
	}

	public Result toggleActiveStatus(Integer rolePermissionId, String entityId) throws XOException {
		JsonNode jsonResponse = generateErrorResponse(Messages.get(ERR_INVALID_RLP_ID));
		try {
			if(rolePermissionId > 0 && XoUtil.isNotNull(entityId)) {
				this.rolePermissionResourceInstanceLogic.toggleActiveStatus(new RolePermissionResourceInstanceId(rolePermissionId, entityId));
				jsonResponse = generateSuccessResponse(Messages.get(MSG_RLP_STATUS_CHANGED_SUCCESSFULLY));
			}
		} catch (Exception e) {
			Logger.error("Error while changing the RLP status.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_RLP_STATUS_CHANGE_FAILED));
			throw new XOException(e);
		} finally{			
			return ok(jsonResponse);
		}
	}

	public Result delete(Integer rolePermissionId, String entityId) throws XOException {
		JsonNode jsonResponse = generateErrorResponse(Messages.get(ERR_INVALID_RLP_ID));
		try {
			if(rolePermissionId > 0 && XoUtil.isNotNull(entityId)) {
				this.entityLogic.removeById(new RolePermissionResourceInstanceId(rolePermissionId, entityId));
				jsonResponse = generateSuccessResponse(Messages.get(MSG_RLP_DELETED_SUCCESSFULLY));
			}
		} catch (Exception e) {
			Logger.error("Error while deleting the RLP.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_RLP_DELETE_FAILED));
			throw new XOException(e);
		} finally{			
			return ok(jsonResponse);
		}
	}
}
