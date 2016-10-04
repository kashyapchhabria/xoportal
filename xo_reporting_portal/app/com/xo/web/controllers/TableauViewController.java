package com.xo.web.controllers;

import com.xo.web.viewdtos.MessageDto;
import play.Logger;
import play.i18n.Messages;
import play.libs.Json;
import play.mvc.Result;

import com.fasterxml.jackson.databind.JsonNode;
import com.xo.web.core.XODAOException;
import com.xo.web.core.XOException;
import com.xo.web.ext.tableau.mgr.TableauViewLogic;
import com.xo.web.ext.tableau.models.TableauView;
import com.xo.web.models.system.PermissionEnum;
import com.xo.web.security.authorization.action.Authroize;
import com.xo.web.util.XoUtil;
import com.xo.web.viewdtos.TableauViewDto;

/**
 * @author sekar
 *
 */
@SuppressWarnings({"finally"})
public class TableauViewController extends BaseController<TableauView, String> implements TableauViewI18NLabels {

	private static final String VIEW_GROUP_ID = "viewGroupId";
	private static final String TABLEAU_VIEW_ID = "tableauViewId";
	private final TableauViewLogic tableauViewLogic;

	public TableauViewController() {
		super(new TableauViewLogic());
		this.tableauViewLogic = (TableauViewLogic) this.entityLogic;
	}

	@Authroize(permissions = {PermissionEnum.READ_TABLEAU_VIEW})
	public Result readAll() {
		JsonNode jsonResponse = null;
		try {
			jsonResponse = Json.toJson(this.tableauViewLogic.findAllTableauViews());
		} catch (Exception e) {
			Logger.error("Error while reading the role list.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_UNABLE_TO_LOAD_TABLEAU_VIEWS));
			throw new XOException(e);
		} finally {
			return ok(jsonResponse);
		}
	}

	@Authroize(permissions = {PermissionEnum.READ_TABLEAU_VIEW})
	public Result readAllAsKeyValue() {
		JsonNode jsonResponse = null;
		try {
			jsonResponse = Json.toJson(this.tableauViewLogic.readAllAsKeyValues());
		} catch (Exception e) {
			Logger.error("Error while reading the role list.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_UNABLE_TO_LOAD_TABLEAU_VIEWS));
			throw new XOException(e);
		} finally {
			return ok(jsonResponse);
		}
	}

	@Authroize(permissions = {PermissionEnum.READ_TABLEAU_VIEW, PermissionEnum.ACTIVATE_TABLEAU_VIEW, PermissionEnum.DEACTIVATE_TABLEAU_VIEW})
	public Result toggleActiveStatus(String tableauViewId) throws XOException {
		JsonNode jsonResponse = null;
		try {
			if (XoUtil.isNotNull(tableauViewId)) {
				this.tableauViewLogic.toggleActiveStatus(tableauViewId);
				jsonResponse = generateSuccessResponse(Messages.get(MSG_TABLEAU_VIEW_STATUS_CHANGED_SUCCESSFULLY));
			} else {
				jsonResponse = generateErrorResponse(Messages.get(ERR_INVALID_TABLEAU_VIEW_ID));
			}
		} catch (Exception e) {
			Logger.error("Error while changing the User status.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_TABLEAU_VIEW_STATUS_CHANGE_FAILED));
			throw new XOException(e);
		} finally {
			return ok(jsonResponse);
		}
	}

	@Authroize(permissions = {PermissionEnum.READ_TABLEAU_VIEW})
	public Result read(String tableauViewId) {
		JsonNode jsonResponse = generateErrorResponse(Messages.get(ERR_INVALID_TABLEAU_VIEW_ID));
		if (XoUtil.isNotNull(tableauViewId)) {
			TableauViewDto tableauViewDto = this.tableauViewLogic.read(tableauViewId);
			if (tableauViewDto != null) {
				jsonResponse = Json.toJson(tableauViewDto);
			}
		}
		return ok(jsonResponse);
	}

	@Authroize(permissions = {PermissionEnum.READ_TABLEAU_VIEW, PermissionEnum.UPDATE_TABLEAU_VIEW})
	public Result update() {
		JsonNode json = request().body().asJson();
		JsonNode jsonResponse = null;
		if (json == null) {
			Logger.error(BAD_REQUEST_UNKNOWN_DATA);
			return badRequest(generateErrorResponse(BAD_REQUEST_UNKNOWN_DATA));
		}
		try {
			if (XoUtil.jsonValidate(json, VIEW_GROUP_ID, TABLEAU_VIEW_ID)) {
				TableauViewDto tableauViewDto = Json.fromJson(json, TableauViewDto.class);
				if (this.tableauViewLogic.validateTableauViewDetails(tableauViewDto)) {
					this.tableauViewLogic.update(tableauViewDto);
					jsonResponse = generateSuccessResponse(Messages.get(MSG_TABLEAU_VIEW_UPDATE_SUCCESS));
				}
			} else {
				Logger.error("Json validation failed. Please check the view group details json.");
				jsonResponse = generateErrorResponse(Messages.get(ERR_TABLEAU_VIEW_JSON_INVALID));
			}
		} catch (XODAOException e) {
			Logger.error("Error while udpating the view group details.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_SAVING_TABLEAU_VIEW));
			throw new XOException(e);
		} catch (XOException e) {
			Logger.error("View group has been already registered", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_TABLEAU_VIEW_OR_VIEW_GROUP_NOT_EXISTS));
			throw new XOException(e);
		} catch (Exception e) {
			Logger.error("Error while udpating the view group details.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_SAVING_TABLEAU_VIEW));
			throw new XOException(e);
		} finally {
			return ok(jsonResponse);
		}
	}

	@Authroize(permissions = {PermissionEnum.READ_TABLEAU_VIEW, PermissionEnum.ACTIVATE_TABLEAU_VIEW, PermissionEnum.DEACTIVATE_TABLEAU_VIEW})
	public Result toggleDashboardStatus(String tableauViewId) throws XOException {
		JsonNode jsonResponse = null;
		try {
			if (XoUtil.isNotNull(tableauViewId)) {
				this.tableauViewLogic.toggleDashboard(tableauViewId);
				jsonResponse = generateSuccessResponse(Messages.get(MSG_TABLEAU_VIEW_STATUS_CHANGED_SUCCESSFULLY));
			} else {
				jsonResponse = generateErrorResponse(Messages.get(ERR_INVALID_TABLEAU_VIEW_ID));
			}
		} catch (Exception e) {
			Logger.error("Error while changing the User status.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_TABLEAU_VIEW_STATUS_CHANGE_FAILED));
			throw new XOException(e);
		} finally {
			return ok(jsonResponse);
		}
	}


	public Result isDashboardExist(Integer viewGroupId) {
		JsonNode jsonResponse = null;
		boolean dashboardStatus = true;
		try {
			if(viewGroupId > 0) {
				jsonResponse = new MessageDto(""+this.tableauViewLogic.isDashboardExist(viewGroupId, dashboardStatus)).toJson();
			}
			else {
				jsonResponse = generateErrorResponse(Messages.get(ERR_INVALID_TABLEAU_VIEW_ID));
			}
		} catch (Exception e) {
			Logger.error("Error while reading the group list.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_UNABLE_TO_LOAD_TABLEAU_VIEWS));
			throw new XOException(e);
		} finally{
			return ok(jsonResponse);
		}
	}


}