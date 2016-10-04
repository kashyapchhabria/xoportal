package com.xo.web.controllers;

import play.Logger;
import play.i18n.Messages;
import play.libs.Json;
import play.mvc.Result;

import com.fasterxml.jackson.databind.JsonNode;
import com.xo.web.core.XOException;
import com.xo.web.mgr.UserPermissionResourceInstanceLogic;
import com.xo.web.models.system.RolePermissionResourceInstanceId;
import com.xo.web.models.system.UserPermissionResourceInstance;
import com.xo.web.models.system.UserPermissionResourceInstanceId;
import com.xo.web.util.XoUtil;
import com.xo.web.viewdtos.RowLevelPermissionDto;

@SuppressWarnings("finally")
public class UserPermissionResourceInstanceController extends
		RowLevelPermissionController<UserPermissionResourceInstance, UserPermissionResourceInstanceId> {

	private final UserPermissionResourceInstanceLogic userPermissionResourceInstanceLogic; 

	public UserPermissionResourceInstanceController() {
		super(new UserPermissionResourceInstanceLogic());
		this.userPermissionResourceInstanceLogic = (UserPermissionResourceInstanceLogic) this.entityLogic;
	}

	public Result readAll() {
		return super.readAll();
	}

	public Result readAllByUserAndResourType(Integer userId, Integer resourceTypeId) {
		return super.readAllByRlpAndResourType(userId, resourceTypeId);
	}

	public Result readAllEntityInstances(Integer userId, Integer resourceTypeId) {
		return super.readAllEntityInstances(userId, resourceTypeId);
	}

	public Result create() {
		JsonNode jsonResponse = null;
		try {
			JsonNode json = request().body().asJson();
			RowLevelPermissionDto rowLevelPermissionDto = Json.fromJson(json, RowLevelPermissionDto.class);
			this.userPermissionResourceInstanceLogic.save(rowLevelPermissionDto);
			jsonResponse = generateSuccessResponse(Messages.get(MSG_RLP_ADDED_SUCCESSFULLY));
		} catch (Exception e) {
			Logger.error("Error while reading the RLP list.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_RLP_UNABLE_GRANT_PERMISSION));
			throw new XOException(e);
		} finally{			
			return ok(jsonResponse);
		}
	}

	public Result toggleActiveStatus(Integer userPermissionId, String entityId) throws XOException {
		JsonNode jsonResponse = generateErrorResponse(Messages.get(ERR_INVALID_RLP_ID));
		try {
			if(userPermissionId > 0 && XoUtil.isNotNull(entityId)) {
				this.userPermissionResourceInstanceLogic.toggleActiveStatus(new UserPermissionResourceInstanceId(userPermissionId, entityId));
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

	public Result delete(Integer userPermissionId, String entityId) throws XOException {
		JsonNode jsonResponse = generateErrorResponse(Messages.get(ERR_INVALID_RLP_ID));
		try {
			if(userPermissionId > 0 && XoUtil.isNotNull(entityId)) {
				this.entityLogic.removeById(new UserPermissionResourceInstanceId(userPermissionId, entityId));
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
