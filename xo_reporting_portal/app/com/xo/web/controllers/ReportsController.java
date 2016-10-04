package com.xo.web.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.xo.web.core.XOException;
import com.xo.web.ext.tableau.mgr.ReportsManagmentLogic;
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
public class ReportsController{

//
//    public PermissionController() {
//        super(new PermissionLogic());
//        this.permissionLogic = (PermissionLogic) this.entityLogic;
//    }
//
//    @Authroize(permissions = {PermissionEnum.READ_PERMISSION})
//    public Result readAll() {
//        JsonNode jsonResponse = null;
//        try {
//            jsonResponse = Json.toJson(this.permissionLogic.findAllPermissions());
//        } catch (Exception e) {
//            Logger.error("Error while reading the permission list.", e);
//            jsonResponse = generateErrorResponse(Messages.get(ERR_UNABLE_TO_LOAD_PERMISSIONS));
//            throw new XOException(e);
//        } finally{
//            return ok(jsonResponse);
//        }
//    }
//
}
