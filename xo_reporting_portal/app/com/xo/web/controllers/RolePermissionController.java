package com.xo.web.controllers;

import play.Logger;
import play.i18n.Messages;
import play.libs.Json;
import play.mvc.Result;

import com.fasterxml.jackson.databind.JsonNode;
import com.xo.web.core.XOException;
import com.xo.web.mgr.RolePermissionLogic;
import com.xo.web.models.system.PermissionEnum;
import com.xo.web.models.system.RolePermission;
import com.xo.web.security.authorization.action.Authroize;

@SuppressWarnings({"finally"})
public class RolePermissionController extends BaseController<RolePermission,Integer> implements RolePermissionI18NLabels {

	private final RolePermissionLogic rolePermissionLogic;

	public RolePermissionController() {
		super(new RolePermissionLogic());
		this.rolePermissionLogic = (RolePermissionLogic) this.entityLogic;
	}

	@Authroize(permissions = {PermissionEnum.READ_ROLE_PERMISSION})
	public Result readAll(Integer roleId) {
		JsonNode jsonResponse = null;
		try {
			if(roleId > 0) {
				jsonResponse = Json.toJson(this.rolePermissionLogic.findAllRolesPermission(roleId));
			} else {
				jsonResponse = generateErrorResponse(Messages.get(ERR_INVALID_ROLE_ID));
			}
		} catch (Exception e) {
			Logger.error("Error while reading the role's permission  list.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_UNABLE_TO_LOAD_ROLES_PERMISSION));
			throw new XOException(e);
		} finally{
			return ok(jsonResponse);
		}
	}

	@Authroize(permissions = {PermissionEnum.READ_ROLE_PERMISSION, PermissionEnum.ACTIVATE_ROLE_PERMISSION, PermissionEnum.DEACTIVATE_ROLE_PERMISSION})
	public Result toggleActiveStatus(Integer rolePermissionId) throws XOException {
		JsonNode jsonResponse = null;
		try {
			if(rolePermissionId > 0) {
				this.rolePermissionLogic.toggleActiveStatus(rolePermissionId);
				jsonResponse = generateSuccessResponse(Messages.get(MSG_ROLE_PERMISSION_STATUS_CHANGED_SUCCESSFULLY));
			} else {
				jsonResponse = generateErrorResponse(Messages.get(ERR_INVALID_ROLE_PERMISSION_ID));
			}
		} catch (Exception e) {
			Logger.error("Error while changing the role's permission status.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_ROLE_PERMISSION_STATUS_CHANGE_FAILED));
			throw new XOException(e);
		} finally{
			return ok(jsonResponse);
		}
	}

	@Authroize(permissions = {PermissionEnum.READ_ROLE_PERMISSION, PermissionEnum.DELETE_ROLE_PERMISSION})
	public Result delete(Integer rolePermissionId) {
		JsonNode jsonResponse = null;
		try {
			if(rolePermissionId > 0) {
				this.rolePermissionLogic.removeById(rolePermissionId);
			}
			jsonResponse = generateSuccessResponse(Messages.get(MSG_ROLE_PERMISSION_DELETED_SUCCESSFULLY));
		} catch (Exception e) {
			Logger.error("Error while changing the role's permission status.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_ROLE_PERMISSION_DELETE_FAILED));
			throw new XOException(e);
		} finally{
			return ok(jsonResponse);
		}
	}

    @Authroize(permissions = {PermissionEnum.CREATE_ROLE_PERMISSION})
    public Result create(Integer roleId, Integer permissionId) throws XOException {
        JsonNode jsonResponse = null;
        try {
        	if(roleId > 0 && permissionId > 0) {
        		RolePermission rolePermission = this.rolePermissionLogic.create(roleId, permissionId);
        		jsonResponse = generateSuccessResponse(Messages.get(MSG_ROLE_PERMISSION_ADDED_SUCCESSFULLY,rolePermission.getPermission().getName()));
        	} else {
        		jsonResponse = generateSuccessResponse(Messages.get(ERR_INVALID_ROLE_PERMISSION_ID));
        	}
        } catch (Exception e) {
            Logger.error("Error while creating the role permission.", e);
            jsonResponse = generateErrorResponse(Messages.get(ERR_ROLE_PERMISSION_STATUS_CHANGE_FAILED));
            throw new XOException(e);
        } finally{
            return ok(jsonResponse);
        }
    }

}
