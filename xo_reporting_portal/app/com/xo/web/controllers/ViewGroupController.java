package com.xo.web.controllers;

import play.Logger;
import play.i18n.Messages;
import play.libs.Json;
import play.mvc.Result;

import com.fasterxml.jackson.databind.JsonNode;
import com.xo.web.core.XODAOException;
import com.xo.web.core.XOException;
import com.xo.web.ext.tableau.mgr.ViewGroupLogic;
import com.xo.web.ext.tableau.models.ViewGroup;
import com.xo.web.models.system.PermissionEnum;
import com.xo.web.security.authorization.action.Authroize;
import com.xo.web.util.XoUtil;
import com.xo.web.viewdtos.ViewGroupDto;

/**
 * @author sekar
 *
 */
@SuppressWarnings({"finally"})
public class ViewGroupController extends BaseController<ViewGroup, Integer> implements ViewGroupI18NLabels {

	private static final String VIEW_GROUP_ID = "viewGroupId";
	private static final String VIEW_GROUP_NAME = "groupName";
	private final ViewGroupLogic viewGroupLogic;

	public ViewGroupController() {
		super(new ViewGroupLogic());
		this.viewGroupLogic = (ViewGroupLogic) this.entityLogic;
	}

	@Authroize(permissions = {PermissionEnum.READ_VIEW_GROUP})
	public Result readAll() {
		JsonNode jsonResponse = null;
		try {
			jsonResponse = Json.toJson(this.viewGroupLogic.findAllViewGroups());
		} catch (Exception e) {
			Logger.error("Error while reading the group list.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_UNABLE_TO_LOAD_VIEW_GROUPS));
			throw new XOException(e);
		} finally{
			return ok(jsonResponse);
		}
	}

	@Authroize(permissions = {PermissionEnum.READ_VIEW_GROUP})
	public Result readAllAsKeyValue() {
		JsonNode jsonResponse = null;
		try {
			jsonResponse = Json.toJson(this.viewGroupLogic.readAllAsKeyValues());
		} catch (Exception e) {
			Logger.error("Error while reading the groups list.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_UNABLE_TO_LOAD_VIEW_GROUPS));
			throw new XOException(e);
		} finally{
			return ok(jsonResponse);
		}
	}

	@Authroize(permissions = {PermissionEnum.CREATE_VIEW_GROUP})
	public Result create() {
		JsonNode json = request().body().asJson();
		JsonNode jsonResponse = null;
		if (json == null) {
			Logger.error(BAD_REQUEST_UNKNOWN_DATA);
			return badRequest(generateErrorResponse(BAD_REQUEST_UNKNOWN_DATA));
		}
		try {
			if (XoUtil.jsonValidate(json, VIEW_GROUP_NAME)) {
				ViewGroupDto viewGroupDto = Json.fromJson(json, ViewGroupDto.class);
				if(this.viewGroupLogic.validateViewGroupDetails(viewGroupDto)) {
					ViewGroup viewGroup = this.viewGroupLogic.save(viewGroupDto);					
					jsonResponse = generateSuccessResponse(Messages.get(MSG_CREATED_SUCCESSFULLY, viewGroup.getGroupName()));
				}
			} else {
				Logger.error("Json validation failed. Please check the Role details json.");
				jsonResponse = generateErrorResponse(Messages.get(ERR_VIEW_GROUP_JSON_INVALID));
			}
		} catch (XODAOException e) {
			Logger.error("Error while saving the Role details.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_SAVING_VIEW_GROUP));
			throw new XOException(e);
		} catch (XOException e) {
			Logger.error("Role has been already registered", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_VIEW_GROUP_ALREADY_REGISTERED));
			throw new XOException(e);
		} catch(Exception e) {
			Logger.error("Error while saving the Role details.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_SAVING_VIEW_GROUP));
			throw new XOException(e);
		} finally {
			return ok(jsonResponse);
		}
	}

	@Authroize(permissions = {PermissionEnum.READ_VIEW_GROUP, PermissionEnum.ACTIVATE_VIEW_GROUP, PermissionEnum.DEACTIVATE_VIEW_GROUP})
	public Result toggleActiveStatus(Integer viewGroupId) throws XOException {
		JsonNode jsonResponse = null;
		try {
			if(viewGroupId > 0) {
				this.viewGroupLogic.toggleActiveStatus(viewGroupId);
				jsonResponse = generateSuccessResponse(Messages.get(MSG_VIEW_GROUP_STATUS_CHANGED_SUCCESSFULLY));
			} else {
				jsonResponse = generateErrorResponse(Messages.get(ERR_INVALID_VIEW_GROUP_ID));
			}
		} catch (Exception e) {
			Logger.error("Error while changing the User status.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_VIEW_GROUP_STATUS_CHANGE_FAILED));
			throw new XOException(e);
		} finally{
			return ok(jsonResponse);
		}
	}

	@Authroize(permissions = {PermissionEnum.READ_VIEW_GROUP})
	public Result read(Integer viewGroupId) {
		JsonNode jsonResponse = generateErrorResponse(Messages.get(ERR_INVALID_VIEW_GROUP_ID));
		if(viewGroupId > 0) {
			ViewGroupDto viewGroupDto = this.viewGroupLogic.read(viewGroupId);
			if(viewGroupDto != null) {
				jsonResponse = Json.toJson(viewGroupDto);
			}
		}
		return ok(jsonResponse);
	}

	@Authroize(permissions = {PermissionEnum.READ_VIEW_GROUP, PermissionEnum.UPDATE_VIEW_GROUP})
	public Result update() {
		JsonNode json = request().body().asJson();
		JsonNode jsonResponse = null;
		if (json == null) {
			Logger.error(BAD_REQUEST_UNKNOWN_DATA);
			return badRequest(generateErrorResponse(BAD_REQUEST_UNKNOWN_DATA));
		}
		try {
			if (XoUtil.jsonValidate(json, VIEW_GROUP_ID, VIEW_GROUP_NAME)) {
				ViewGroupDto viewGroupDto = Json.fromJson(json, ViewGroupDto.class);
				if(this.viewGroupLogic.validateViewGroupDetails(viewGroupDto)) {
					this.viewGroupLogic.update(viewGroupDto);
					jsonResponse = generateSuccessResponse(Messages.get(MSG_VIEW_GROUP_UPDATE_SUCCESS));
				}
			} else {
				Logger.error("Json validation failed. Please check the view group details json.");
				jsonResponse = generateErrorResponse(Messages.get(ERR_VIEW_GROUP_JSON_INVALID));
			}
		} catch (XODAOException e) {
			Logger.error("Error while udpating the view group details.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_SAVING_VIEW_GROUP));
			throw new XOException(e);
		} catch (XOException e) {
			Logger.error("View group has been already registered", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_VIEW_GROUP_ALREADY_REGISTERED));
			throw new XOException(e);
		} catch(Exception e) {
			Logger.error("Error while udpating the view group details.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_SAVING_VIEW_GROUP));
			throw new XOException(e);
		} finally{
			return ok(jsonResponse);
		}
	}

	@Authroize(permissions = {PermissionEnum.READ_VIEW_GROUP, PermissionEnum.DELETE_VIEW_GROUP})
	public Result delete(Integer viewGroupId) {
		JsonNode jsonResponse = null;
		try {
			if(viewGroupId > 0) {
				this.viewGroupLogic.delete(viewGroupId);
			}
			jsonResponse = generateSuccessResponse(Messages.get(MSG_VIEW_GROUP_DELETED_SUCCESSFULLY));
		} catch (Exception e) {
			Logger.error("Error while changing the User role status.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_VIEW_GROUP_DELETE_FAILED));
			throw new XOException(e);
		} finally{
			return ok(jsonResponse);
		}
	}

	public Result readGroupCount(Integer viewGroupId) {
		JsonNode jsonResponse = null;
		try {
			if(viewGroupId > 0) {
				jsonResponse = Json.toJson(this.viewGroupLogic.readGroupCount(viewGroupId));
			} else {
				jsonResponse = generateErrorResponse(Messages.get(ERR_INVALID_VIEW_GROUP_ID));
			}
		} catch (Exception e) {
			Logger.error("Error while reading the group list.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_UNABLE_TO_LOAD_VIEW_GROUPS));
			throw new XOException(e);
		} finally{
			return ok(jsonResponse);
		}
	}


}

