package com.xo.web.controllers;

import play.Logger;
import play.i18n.Messages;
import play.libs.Json;
import play.mvc.Result;

import com.fasterxml.jackson.databind.JsonNode;
import com.xo.web.core.XOException;
import com.xo.web.mgr.UserPermissionLogic;
import com.xo.web.models.system.PermissionEnum;
import com.xo.web.models.system.UserPermission;
import com.xo.web.security.authorization.action.Authroize;

@SuppressWarnings("finally")
public class UserPermissionController extends BaseController<UserPermission, Integer> implements UserPermissionI18NLabels {

	private final UserPermissionLogic userPermissionLogic;

	public UserPermissionController() {
		super(new UserPermissionLogic());
		this.userPermissionLogic = (UserPermissionLogic) this.entityLogic;
	}

	@Authroize(permissions = {PermissionEnum.READ_USER_PERMISSION})
	public Result readAll(Integer userId) {
		JsonNode jsonResponse = null;
		try {
			jsonResponse = Json.toJson(this.userPermissionLogic.findAllUserPermissions(userId));
		} catch (Exception e) {
			Logger.error("Error while reading the user's permission list.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_UNABLE_TO_LOAD_USER_PERMISSIONS));
			throw new XOException(e);
		} finally{
			return ok(jsonResponse);
		}
	}

	@Authroize(permissions = {PermissionEnum.READ_USER_PERMISSION, PermissionEnum.ACTIVATE_USER_PERMISSION, PermissionEnum.DEACTIVATE_USER_PERMISSION})
	public Result toggleActiveStatus(Integer userpermissionId) throws XOException {
		JsonNode jsonResponse = null;
		try {
			this.userPermissionLogic.toggleUserStatus(userpermissionId);
			jsonResponse = generateSuccessResponse(Messages.get(MSG_USER_PERMISSION_STATUS_CHANGED_SUCCESSFULLY));
		} catch (Exception e) {
			Logger.error("Error while changing the User permission status.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_USER_PERMISSION_STATUS_CHANGE_FAILED));
			throw new XOException(e);
		} finally{			
			return ok(jsonResponse);
		}
	}

	@Authroize(permissions = {PermissionEnum.READ_USER_PERMISSION,PermissionEnum.CREATE_USER_PERMISSION})
	public Result create(Integer userId, Integer permissionId) throws XOException {
		JsonNode jsonResponse = null;
		try {
			UserPermission userPermission = this.userPermissionLogic.create(userId, permissionId);
			jsonResponse = generateSuccessResponse(Messages.get(MSG_PERMISSION_STATUS_CHANGED_SUCCESSFULLY,userPermission.getPermission().getName()));
		} catch (Exception e) {
			Logger.error("Error while changing the User permission status.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_USER_PERMISSION_STATUS_CHANGE_FAILED));
			throw new XOException(e);
		} finally{			
			return ok(jsonResponse);
		}
	}

	@Authroize(permissions = { PermissionEnum.READ_USER_PERMISSION,PermissionEnum.DELETE_USER_PERMISSION})
	public Result delete(Integer userpermissionId){
		JsonNode jsonResponse = null;
		try {
			this.userPermissionLogic.removeById(userpermissionId);
			jsonResponse = generateSuccessResponse(Messages.get(MSG_USER_PERMISSION_DELETED_SUCCESSFULLY));
		} catch (Exception e) {
			Logger.error("Error while changing the User permission status.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_USER_PERMISSION_DELETE_FAILED));
			throw new XOException(e);
		} finally{			
			return ok(jsonResponse);
		}
	}	
	
}
