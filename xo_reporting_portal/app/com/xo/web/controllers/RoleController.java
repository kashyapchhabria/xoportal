package com.xo.web.controllers;

import play.Logger;
import play.i18n.Messages;
import play.libs.Json;
import play.mvc.Result;

import com.fasterxml.jackson.databind.JsonNode;
import com.xo.web.core.XODAOException;
import com.xo.web.core.XOException;
import com.xo.web.mgr.RoleLogic;
import com.xo.web.models.system.PermissionEnum;
import com.xo.web.models.system.Role;
import com.xo.web.security.authorization.action.Authroize;
import com.xo.web.util.XoUtil;
import com.xo.web.viewdtos.RoleDto;

/**
 * @author sekar
 *
 */
@SuppressWarnings({"finally"})
public class RoleController extends BaseController<Role, Integer> implements RoleI18NLabels {

	private final RoleLogic roleLogic;

	public RoleController() {
		super(new RoleLogic());
		this.roleLogic = (RoleLogic) this.entityLogic;
	}
	
	@Authroize(permissions = {PermissionEnum.READ_ROLE})
	public Result readAll() {
		JsonNode jsonResponse = null;
		try {
			jsonResponse = Json.toJson(this.roleLogic.findAllRoles());
		} catch (Exception e) {
			Logger.error("Error while reading the role list.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_UNABLE_TO_LOAD_ROLES));
			throw new XOException(e);
		} finally{
			return ok(jsonResponse);
		}
	}

	@Authroize(permissions = {PermissionEnum.READ_ROLE})
	public Result readAllAsKeyValue() {
		JsonNode jsonResponse = null;
		try {
			jsonResponse = Json.toJson(this.roleLogic.readAllAsKeyValues());
		} catch (Exception e) {
			Logger.error("Error while reading the role list.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_UNABLE_TO_LOAD_ROLES));
			throw new XOException(e);
		} finally{
			return ok(jsonResponse);
		}
	}

	@Authroize(permissions = {PermissionEnum.READ_ROLE})
	public Result readUnassigned(Integer userId) {
		JsonNode jsonResponse = null;
		try {
			jsonResponse = Json.toJson(this.roleLogic.readUnassigned(userId));
		} catch (Exception e) {
			Logger.error("Error while reading the role list.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_UNABLE_TO_LOAD_ROLES));
			throw new XOException(e);
		} finally{
			return ok(jsonResponse);
		}
	}

	@Authroize(permissions = {PermissionEnum.CREATE_ROLE})
	public Result create() {
		JsonNode json = request().body().asJson();
		JsonNode jsonResponse = null;
		if (json == null) {
			Logger.error(BAD_REQUEST_UNKNOWN_DATA);
			return badRequest(generateErrorResponse(BAD_REQUEST_UNKNOWN_DATA));
		}
		try {
			if (XoUtil.jsonValidate(json, "name")) {
				RoleDto roleDto = Json.fromJson(json, RoleDto.class);
				if(this.roleLogic.validateRoleDetails(roleDto)) {
					Role role = this.roleLogic.save(roleDto);					
					jsonResponse = generateSuccessResponse(Messages.get(CREATED_SUCCESSFULLY,role.getName()));
				}
			} else {
				Logger.error("Json validation failed. Please check the Role details json.");
				jsonResponse = generateErrorResponse(Messages.get(ERR_ROLE_JSON_INVALID));
			}
		} catch (XODAOException e) {
			Logger.error("Error while saving the Role details.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_SAVING_ROLE));
			throw new XOException(e);
		} catch (XOException e) {
			Logger.error("Role has been already registered", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_ROLE_ALREADY_REGISTERED));
			throw new XOException(e);
		} catch(Exception e) {
			Logger.error("Error while saving the Role details.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_SAVING_ROLE));
			throw new XOException(e);
		} finally {
			return ok(jsonResponse);
		}
	}

	@Authroize(permissions = {PermissionEnum.READ_ROLE, PermissionEnum.ACTIVATE_ROLE, PermissionEnum.DEACTIVATE_ROLE})
	public Result toggleActiveStatus(Integer roleId) throws XOException {
		JsonNode jsonResponse = null;
		try {
			if(roleId > 0) {
				this.roleLogic.toggleActiveStatus(roleId);
				jsonResponse = generateSuccessResponse(Messages.get(MSG_ROLE_STATUS_CHANGED_SUCCESSFULLY));
			} else {
				jsonResponse = generateErrorResponse(Messages.get(ERR_INVALID_ROLE_ID));
			}
		} catch (Exception e) {
			Logger.error("Error while changing the User status.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_ROLE_STATUS_CHANGE_FAILED));
			throw new XOException(e);
		} finally{
			return ok(jsonResponse);
		}
	}

	@Authroize(permissions = {PermissionEnum.READ_ROLE})
	public Result read(Integer id) {
		JsonNode jsonResponse = generateErrorResponse(Messages.get(ERR_INVALID_ROLE_ID));
		if(id > 0) {
			RoleDto roleDto = this.roleLogic.read(id);
			if(roleDto != null) {
				jsonResponse = Json.toJson(roleDto);
			}
		}
		return ok(jsonResponse);
	}

	@Authroize(permissions = {PermissionEnum.READ_ROLE, PermissionEnum.UPDATE_ROLE})
	public Result update(Integer id) {
		JsonNode json = request().body().asJson();
		JsonNode jsonResponse = null;
		if (json == null) {
			Logger.error(BAD_REQUEST_UNKNOWN_DATA);
			return badRequest(generateErrorResponse(BAD_REQUEST_UNKNOWN_DATA));
		}
		try {
			if (XoUtil.jsonValidate(json, "roleId")) {
				RoleDto roleDto = Json.fromJson(json, RoleDto.class);
				if(this.roleLogic.validateRoleDetails(roleDto)) {
					this.roleLogic.update(roleDto);
					jsonResponse = generateSuccessResponse(Messages.get(MSG_ROLE_UPDATE_SUCCESS));
				}
			} else {
				Logger.error("Json validation failed. Please check the role details json.");
				jsonResponse = generateErrorResponse(Messages.get(ERR_ROLE_JSON_INVALID));
			}
		} catch (XODAOException e) {
			Logger.error("Error while udpating the Role details.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_SAVING_ROLE));
			throw new XOException(e);
		} catch (XOException e) {
			Logger.error("Role has been already registered", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_ROLE_ALREADY_REGISTERED));
			throw new XOException(e);
		} catch(Exception e) {
			Logger.error("Error while udpating the role details.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_SAVING_ROLE));
			throw new XOException(e);
		} finally{			
			return ok(jsonResponse);
		}
	}

	@Authroize(permissions = {PermissionEnum.READ_ROLE, PermissionEnum.DELETE_ROLE})
	public Result delete(Integer roleId) {
		JsonNode jsonResponse = null;
		try {
			if(roleId > 0) {
				this.roleLogic.delete(roleId);
			}
			jsonResponse = generateSuccessResponse(Messages.get(MSG_ROLE_DELETED_SUCCESSFULLY));
		} catch (Exception e) {
			Logger.error("Error while changing the User role status.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_ROLE_DELETE_FAILED));
			throw new XOException(e);
		} finally{
			return ok(jsonResponse);
		}
	}

}
