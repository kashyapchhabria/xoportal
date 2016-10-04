package com.xo.web.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.xo.web.core.XOException;
import com.xo.web.mgr.UserRoleLogic;
import com.xo.web.models.system.PermissionEnum;
import com.xo.web.models.system.Role;
import com.xo.web.models.system.UserRole;
import com.xo.web.models.system.UserRoleId;
import com.xo.web.security.authorization.action.Authroize;
import play.Logger;
import play.i18n.Messages;
import play.libs.Json;
import play.mvc.Result;

@SuppressWarnings("finally")
public class UserRoleController extends BaseController<UserRole, UserRoleId> implements UserRoleI18NLabels {

	private final UserRoleLogic userRoleLogic;

	public UserRoleController() {
		super(new UserRoleLogic());
		this.userRoleLogic = (UserRoleLogic) this.entityLogic;
	}

	@Authroize(permissions = {PermissionEnum.READ_USER_ROLE})
	public Result readAll(Integer userId) {
		JsonNode jsonResponse = null;
		try {
			if(userId > 0) {
				jsonResponse = Json.toJson(this.userRoleLogic.findAllUserRoles(userId));
			} else {
				jsonResponse = generateErrorResponse(Messages.get(ERR_USER_ROLE_INVALID_ID));
			}
		} catch (Exception e) {
			Logger.error("Error while reading the user's role list.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_UNABLE_TO_LOAD_USER_ROLES));
			throw new XOException(e);
		} finally{
			return ok(jsonResponse);
		}
	}

	@Authroize(permissions = {PermissionEnum.READ_USER_ROLE, PermissionEnum.ACTIVATE_USER_ROLE, PermissionEnum.DEACTIVATE_USER_ROLE})
	public Result toggleActiveStatus(Integer userId, Integer roleId) throws XOException {
		JsonNode jsonResponse = null;
		try {
			if(userId > 0 && roleId > 0) {
				this.userRoleLogic.toggleActiveStatus(new UserRoleId(userId, roleId));
				jsonResponse = generateSuccessResponse(Messages.get(MSG_USER_ROLE_STATUS_CHANGED_SUCCESSFULLY));
			} else {
				jsonResponse = generateErrorResponse(Messages.get(ERR_USER_ROLE_INVALID_ID));
			}
		} catch (Exception e) {
			Logger.error("Error while changing the User role status.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_USER_ROLE_STATUS_CHANGE_FAILED));
			throw new XOException(e);
		} finally{			
			return ok(jsonResponse);
		}
	}

	@Authroize(permissions = {PermissionEnum.READ_USER_ROLE,PermissionEnum.CREATE_USER_ROLE})
	public Result create(Integer userId, Integer roleId) throws XOException {
		JsonNode jsonResponse = null;
		try {
			if(userId > 0  && roleId > 0 ) {
				Role role = this.userRoleLogic.create(userId, roleId);
				jsonResponse = generateSuccessResponse(Messages.get(MSG_ROLE_STATUS_CHANGED_SUCCESSFULLY,role.getName()));
			} else {
				jsonResponse = generateErrorResponse(Messages.get(ERR_USER_ROLE_INVALID_ID));
			}
		} catch (Exception e) {
			Logger.error("Error while changing the User role status.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_USER_ROLE_STATUS_CHANGE_FAILED));
			throw new XOException(e);
		} finally{			
			return ok(jsonResponse);
		}
	}

	@Authroize(permissions = { PermissionEnum.READ_USER_ROLE,PermissionEnum.DELETE_USER_ROLE})
	public Result delete(Integer userId, Integer roleId) throws XOException {
		JsonNode jsonResponse = null;
		try {
			if(userId > 0  && roleId > 0 ) {
				this.userRoleLogic.removeById(new UserRoleId(userId, roleId));
			}
			jsonResponse = generateSuccessResponse(Messages.get(MSG_USER_ROLE_DELETED_SUCCESSFULLY));
		} catch (Exception e) {
			Logger.error("Error while changing the User role status.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_USER_ROLE_DELETE_FAILED));
			throw new XOException(e);
		} finally{			
			return ok(jsonResponse);
		}
	}


    @Authroize(permissions = {PermissionEnum.READ_ROLE})
    public Result readRolesCount(Integer roleId) {
        JsonNode jsonResponse = null;
        try {
            if(roleId > 0) {
                jsonResponse = Json.toJson(this.userRoleLogic.readRolesCount(roleId));
            } else {
                jsonResponse = generateErrorResponse(Messages.get(ERR_USER_ROLE_INVALID_ID));
            }
        } catch (Exception e) {
            Logger.error("Error while reading the permission list.", e);
            jsonResponse = generateErrorResponse(Messages.get(ERR_UNABLE_TO_LOAD_USER_ROLES));
            throw new XOException(e);
        } finally{
            return ok(jsonResponse);
        }
    }
}
