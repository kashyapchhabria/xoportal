package com.xo.web.controllers;

import play.Logger;
import play.i18n.Messages;
import play.libs.Json;
import play.mvc.Result;

import com.fasterxml.jackson.databind.JsonNode;
import com.xo.web.core.XODAOException;
import com.xo.web.core.XOException;
import com.xo.web.mgr.XoConfigTemplateLogic;
import com.xo.web.models.system.PermissionEnum;
import com.xo.web.models.system.XoConfigTemplate;
import com.xo.web.security.authorization.action.Authroize;
import com.xo.web.util.XoUtil;
import com.xo.web.viewdtos.ConfigurationDto;

@SuppressWarnings("finally")
public class XoConfigTemplateController extends XoConfigController<XoConfigTemplate> {

	private final XoConfigTemplateLogic xoConfigTemplateLogic;

    public XoConfigTemplateController() {
        super(new XoConfigTemplateLogic());
        this.xoConfigTemplateLogic = (XoConfigTemplateLogic) this.entityLogic;
    }

    @Authroize(permissions = {PermissionEnum.READ_CONFIGURATION_TEMPLATE})
    public Result readAll() {
    	return super.readAll();
    }

    @Authroize(permissions = {PermissionEnum.READ_CONFIGURATION_TEMPLATE})
    public Result read(Integer configId) {
    	return super.read(configId);
    }

    @Authroize(permissions = {PermissionEnum.CREATE_CONFIGURATION_TEMPLATE})
    public Result create() {
        JsonNode json = request().body().asJson();
        JsonNode jsonResponse = null;
        ConfigurationDto configurationDto = null;
        if (json == null) {
            Logger.error(BAD_REQUEST_UNKNOWN_DATA);
            return badRequest(generateErrorResponse(BAD_REQUEST_UNKNOWN_DATA));
        }
        try {
            if(XoUtil.jsonValidate(json, SHORT_NAME) && XoUtil.jsonValidate(json, CONFIG_JSON)) {
                configurationDto = Json.fromJson(json, ConfigurationDto.class);
                ConfigurationDto createdDto = this.xoConfigTemplateLogic.save(configurationDto);
                jsonResponse = generateSuccessResponse(Messages.get(MSG_CONFIGTEMPLATE_CREATED_SUCCESSFULLY, createdDto.shortName), createdDto);
            } else {
                Logger.error("Json validation failed. Please check the role details json.");
                jsonResponse = generateErrorResponse(Messages.get(ERR_CONFIGTEMPLATE_JSON_INVALID));
            }
        } catch (XODAOException e) {
            Logger.error("Error while saving the Configuration template details.", e);
            jsonResponse = generateErrorResponse(Messages.get(ERR_SAVING_CONFIGTEMPLATE));
            throw new XOException(e);
        } catch(Exception e) {
            Logger.error("Error while saving the role details.", e);
            jsonResponse = generateErrorResponse(Messages.get(ERR_SAVING_CONFIGTEMPLATE));
            throw new XOException(e);
        } finally{
            return ok(jsonResponse);
        }
    }

	@Authroize(permissions = {PermissionEnum.READ_CONFIGURATION_TEMPLATE, PermissionEnum.UPDATE_CONFIGURATION_TEMPLATE})
    public Result update(Integer id) {
        JsonNode json = request().body().asJson();
        JsonNode jsonResponse = null;
        ConfigurationDto configurationDto = null;
        if (json == null) {
            Logger.error(BAD_REQUEST_UNKNOWN_DATA);
            return badRequest(generateErrorResponse(BAD_REQUEST_UNKNOWN_DATA));
        }
        try {
            if (XoUtil.jsonValidate(json, SHORT_NAME)) {
                configurationDto = Json.fromJson(json, ConfigurationDto.class);
                this.xoConfigTemplateLogic.update(configurationDto);
                jsonResponse = generateSuccessResponse(Messages.get(MSG_CONFIGTEMPLATE_UPDATE_SUCCESS, configurationDto.shortName));
            } else {
                Logger.error("Json validation failed. Please check the role details json.");
                jsonResponse = generateErrorResponse(Messages.get(ERR_CONFIGTEMPLATE_JSON_INVALID));
            }
        } catch (XODAOException e) {
            Logger.error("Error while updating the Configuration details.", e);
            jsonResponse = generateErrorResponse(Messages.get(ERR_SAVING_CONFIGTEMPLATE));
            throw new XOException(e);
        } catch(Exception e) {
            Logger.error("Error while udpating the role details.", e);
            jsonResponse = generateErrorResponse(Messages.get(ERR_SAVING_CONFIGTEMPLATE));
            throw new XOException(e);
        } finally{
            return ok(jsonResponse);
        }
    }

	@Authroize(permissions = {PermissionEnum.READ_CONFIGURATION_TEMPLATE, PermissionEnum.ACTIVATE_CONFIGURATION_TEMPLATE, 
			PermissionEnum.DEACTIVATE_CONFIGURATION_TEMPLATE})
	public Result toggleActiveStatus(Integer configId) throws XOException {
		return super.toggleActiveStatus(configId);
	}

	@Authroize(permissions = {PermissionEnum.READ_CONFIGURATION_TEMPLATE, PermissionEnum.DELETE_CONFIGURATION_TEMPLATE})
    public Result delete(Integer configId) {
		return super.delete(configId);
	}
}
