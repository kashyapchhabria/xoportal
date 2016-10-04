package com.xo.web.controllers;

import play.Logger;
import play.i18n.Messages;
import play.libs.Json;
import play.mvc.Result;

import com.fasterxml.jackson.databind.JsonNode;
import com.xo.web.core.XOException;
import com.xo.web.mgr.XoConfigLogic;
import com.xo.web.models.system.XoConfig;
import com.xo.web.util.XoUtil;
import com.xo.web.viewdtos.ConfigurationDto;

@SuppressWarnings("finally")
public class XoConfigController<T extends XoConfig> extends BaseController<T, Integer> implements Configurationsl18NLabels{

	protected static final String SHORT_NAME = "shortName";
	protected static final String CONFIG_JSON = "configJson";

    private final XoConfigLogic<T> xoConfigLogic;

    public XoConfigController(XoConfigLogic<T> xoConfigLogic) {
        super(xoConfigLogic);
        this.xoConfigLogic = (XoConfigLogic<T>) this.entityLogic;
    }

	public Result readAll() {
        JsonNode jsonResponse = null;
        try {
            jsonResponse = Json.toJson(this.xoConfigLogic.findAllConfigs());
        } catch (Exception e) {
            Logger.error("Error while reading the permission list.", e);
            jsonResponse = generateErrorResponse(Messages.get(ERR_UNABLE_TO_LOAD_CONFIGURATIONS));
            throw new XOException(e);
        } finally{
            return ok(jsonResponse);
        }
    }

	public Result read(Integer configId) {
		JsonNode jsonResponse = generateErrorResponse(Messages.get(ERR_INVALID_CONFIGURATION_ID));
		if(configId > 0) {
			ConfigurationDto configDto = this.xoConfigLogic.read(configId);
			if(configDto != null) {
				jsonResponse = configDto.toJson();
			}
		}
		return ok(jsonResponse);
	}

    public Result delete(Integer configId) {
        JsonNode jsonResponse = null;
        try {
            if(configId > 0) {
                this.xoConfigLogic.removeById(configId);
            }
            jsonResponse = generateSuccessResponse(Messages.get(MSG_CONFIG_DELETED_SUCCESSFULLY));
        } catch (Exception e) {
            Logger.error("Error while changing the role's permission status.", e);
            jsonResponse = generateErrorResponse(Messages.get(ERR_CONFIG_DELETE_FAILED));
            throw new XOException(e);
        } finally{
            return ok(jsonResponse);
        }
    }

    public Result toggleActiveStatus(Integer configId) throws XOException {
		JsonNode jsonResponse = null;
		try {
			if(configId > 0) {
				this.entityLogic.toggleActiveStatus(configId);
				jsonResponse = generateSuccessResponse(Messages.get(MSG_CONFIG_STATUS_CHANGED_SUCCESSFULLY));
			} else {
				jsonResponse = generateErrorResponse(Messages.get(ERR_INVALID_CONFIGURATION_ID));
			}
		} catch (Exception e) {
			Logger.error("Error while changing the User status.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_CONFIG_CHANGE_FAILED));
			throw new XOException(e);
		} finally{
			return ok(jsonResponse);
		}
	}

    public Result isConfigExist(String configShortName) {
    	JsonNode jsonResponse = null;
		try {
			if(XoUtil.isNotNull(configShortName)) {
				ConfigurationDto configDto = this.xoConfigLogic.findByName(configShortName);
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
}
