package com.xo.web.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.xo.web.core.XODAOException;
import com.xo.web.core.XOException;
import com.xo.web.mgr.XoConfigInstanceLogic;
import com.xo.web.models.system.PermissionEnum;
import com.xo.web.models.system.XoConfigInstance;
import com.xo.web.security.authorization.action.Authroize;
import com.xo.web.util.XoUtil;
import com.xo.web.viewdtos.ConfigurationDto;
import play.Logger;
import play.i18n.Messages;
import play.libs.Json;
import play.mvc.Result;

@SuppressWarnings("finally")
public class XoConfigInstanceController extends XoConfigController<XoConfigInstance> {

    private final XoConfigInstanceLogic xoConfigInstanceLogic;

    public XoConfigInstanceController() {
        super(new XoConfigInstanceLogic());
        this.xoConfigInstanceLogic = (XoConfigInstanceLogic) this.entityLogic;
    }

    @Authroize(permissions = {PermissionEnum.CREATE_CONFIGURATION_INSTANCE})
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
                ConfigurationDto resultDto = this.xoConfigInstanceLogic.save(configurationDto);
                jsonResponse = generateSuccessResponse(Messages.get(MSG_CONFIGINSTANCE_CREATED_SUCCESSFULLY, resultDto.shortName), resultDto);
            } else {
                Logger.error("Json validation failed. Please check the role details json.");
                jsonResponse = generateErrorResponse(Messages.get(ERR_CONFIGINSTANCE_JSON_INVALID));
            }
        } catch (XODAOException e) {
            Logger.error("Error while saving the Configuration instance details.", e);
            jsonResponse = generateErrorResponse(Messages.get(ERR_SAVING_CONFIGINSTANCE));
            throw new XOException(e);
        } catch(Exception e) {
            Logger.error("Error while saving the configuration instance details.", e);
            jsonResponse = generateErrorResponse(Messages.get(ERR_SAVING_CONFIGINSTANCE));
            throw new XOException(e);
        } finally{
            return ok(jsonResponse);
        }
    }

    @Authroize(permissions = {PermissionEnum.READ_CONFIGURATION_INSTANCE})
    public Result read(Integer configId) {
    	return super.read(configId);
    }

    @Authroize(permissions = {PermissionEnum.READ_CONFIGURATION_INSTANCE})
    public Result readAll(Integer configTemplateId) {
        JsonNode jsonResponse = null;
        try {
            jsonResponse = Json.toJson(this.xoConfigInstanceLogic.readAll(configTemplateId));
        } catch (Exception e) {
            Logger.error("Error while reading the permission list.", e);
            jsonResponse = generateErrorResponse(Messages.get(ERR_UNABLE_TO_LOAD_CONFIGURATIONS));
            throw new XOException(e);
        } finally{
            return ok(jsonResponse);
        }
    }

    @Authroize(permissions = {PermissionEnum.READ_CONFIGURATION_INSTANCE})
    public Result readAllInstances() {
        JsonNode jsonResponse = null;
        try {
            jsonResponse = Json.toJson(this.xoConfigInstanceLogic.readAll());
        } catch (Exception e) {
            Logger.error("Error while reading the Instance list.", e);
            jsonResponse = generateErrorResponse(Messages.get(ERR_UNABLE_TO_LOAD_CONFIGURATIONS));
            throw new XOException(e);
        } finally{
            return ok(jsonResponse);
        }
    }


	@Authroize(permissions = {PermissionEnum.READ_CONFIGURATION_INSTANCE, PermissionEnum.UPDATE_CONFIGURATION_TEMPLATE})
    public Result update(Integer id) {
        JsonNode json = request().body().asJson();
        JsonNode jsonResponse = null;
        ConfigurationDto configurationDto = null;
        if (json == null) {
            Logger.error(BAD_REQUEST_UNKNOWN_DATA);
            return badRequest(generateErrorResponse(BAD_REQUEST_UNKNOWN_DATA));
        }
        try {
            if (XoUtil.jsonValidate(json, "roleId")) {
                configurationDto = Json.fromJson(json, ConfigurationDto.class);
                this.xoConfigInstanceLogic.update(configurationDto);
                jsonResponse = generateSuccessResponse(Messages.get(MSG_CONFIGINSTANCE_UPDATE_SUCCESS, configurationDto.shortName));
            } else {
                Logger.error("Json validation failed. Please check the role details json.");
                jsonResponse = generateErrorResponse(Messages.get(ERR_CONFIGINSTANCE_JSON_INVALID));
            }
        } catch (XODAOException e) {
            Logger.error("Error while udpating the Configuration details.", e);
            jsonResponse = generateErrorResponse(Messages.get(ERR_SAVING_CONFIGINSTANCE));
            throw new XOException(e);
        } catch(Exception e) {
            Logger.error("Error while udpating the role details.", e);
            jsonResponse = generateErrorResponse(Messages.get(ERR_SAVING_CONFIGINSTANCE));
            throw new XOException(e);
        } finally{
            return ok(jsonResponse);
        }
    }

	public Result isConfigExist(Integer configTemplateId, String configShortName) {
    	JsonNode jsonResponse = null;
		try {
			if(XoUtil.isNotNull(configShortName)) {
				ConfigurationDto configDto = this.xoConfigInstanceLogic.findByConfigTemplateAndName(configTemplateId, configShortName);
				if(configDto != null) {
					jsonResponse = generateErrorResponse(Messages.get(ERR_CONFIG_ALREADY_REGISTERED, configShortName), configDto);
				} else {
					jsonResponse = generateSuccessResponse(Messages.get(ERR_CONFIG_NOT_FOUND, configShortName));
				}
			}
		} catch (Exception e) {
			Logger.error("Error while searching config using the config short name.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_CONFIG_READ_ERROR_USING_SHORTNAME));
			throw new XOException(e);
		} finally{
			return ok(jsonResponse);
		}
    }

	@Authroize(permissions = {PermissionEnum.READ_CONFIGURATION_INSTANCE, PermissionEnum.ACTIVATE_CONFIGURATION_INSTANCE, 
			PermissionEnum.DEACTIVATE_CONFIGURATION_INSTANCE})
	public Result toggleActiveStatus(Integer configId) throws XOException {
		return super.toggleActiveStatus(configId);
	}

	@Authroize(permissions = {PermissionEnum.DELETE_CONFIGURATION_INSTANCE})
    public Result delete(Integer configId) {
		return super.delete(configId);
	}
}
