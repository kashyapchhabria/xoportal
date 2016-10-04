package com.xo.web.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.xo.web.core.XOException;
import com.xo.web.mgr.PermissionLogic;
import com.xo.web.models.system.Permission;
import com.xo.web.models.system.PermissionEnum;
import com.xo.web.security.authorization.action.Authroize;
import play.Logger;
import play.i18n.Messages;
import play.libs.Json;
import play.mvc.Result;

/**
 * @author sekar
 *
 */
@SuppressWarnings({"finally"})
public class PermissionController extends BaseController<Permission, Integer> implements PermissionsI18NLabels {

    private final PermissionLogic permissionLogic;

    public PermissionController() {
        super(new PermissionLogic());
        this.permissionLogic = (PermissionLogic) this.entityLogic;
    }

    @Authroize(permissions = {PermissionEnum.READ_PERMISSION})
    public Result readAll() {
        JsonNode jsonResponse = null;
        try {
            jsonResponse = Json.toJson(this.permissionLogic.findAllPermissions());
        } catch (Exception e) {
            Logger.error("Error while reading the permission list.", e);
            jsonResponse = generateErrorResponse(Messages.get(ERR_UNABLE_TO_LOAD_PERMISSIONS));
            throw new XOException(e);
        } finally{
            return ok(jsonResponse);
        }
    }

    @Authroize(permissions = {PermissionEnum.READ_PERMISSION, PermissionEnum.ACTIVATE_PERMISSION, PermissionEnum.DEACTIVATE_PERMISSION})
    public Result toggleActiveStatus(Integer permissionId) throws XOException {
        JsonNode jsonResponse = null;
        try {
        	if(permissionId > 0) {
        		this.permissionLogic.toggleActiveStatus(permissionId);
        		jsonResponse = generateSuccessResponse(Messages.get(MSG_PERMISSION_STATUS_CHANGED_SUCCESSFULLY));
        	} else {
        		jsonResponse = generateSuccessResponse(Messages.get(ERR_PERMISSION_STATUS_INVALID_ID));
        	}
        } catch (Exception e) {
            Logger.error("Error while changing the permission status.", e);
            jsonResponse = generateErrorResponse(Messages.get(ERR_PERMISSION_STATUS_CHANGE_FAILED));
            throw new XOException(e);
        } finally{
            return ok(jsonResponse);
        }
    }

    @Authroize(permissions = {PermissionEnum.READ_PERMISSION})
    public Result readUnAssignedRolePermissions(Integer roleId) {
        JsonNode jsonResponse = null;
        try {
        	if(roleId > 0) {
        		jsonResponse = Json.toJson(this.permissionLogic.findUnassignedPermissions(roleId));
        	} else {
        		jsonResponse = generateErrorResponse(Messages.get(ERR_INVALID_ROLE_ID));
        	}
        } catch (Exception e) {
            Logger.error("Error while reading the permission list.", e);
            jsonResponse = generateErrorResponse(Messages.get(ERR_UNABLE_TO_LOAD_PERMISSIONS));
            throw new XOException(e);
        } finally{
            return ok(jsonResponse);
        }
    }


	@Authroize(permissions = {PermissionEnum.READ_PERMISSION})
	public Result readUnAssignedUserPermissions(Integer userId) {
		JsonNode jsonResponse = null;
		try {
			jsonResponse = Json.toJson(this.permissionLogic.readUnassigned(userId));
		} catch (Exception e) {
			Logger.error("Error while reading the permission list.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_UNABLE_TO_LOAD_PERMISSIONS));
			throw new XOException(e);
		} finally{			
			return ok(jsonResponse);
		}
	}

}
