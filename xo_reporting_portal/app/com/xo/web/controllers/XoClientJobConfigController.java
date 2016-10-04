package com.xo.web.controllers;

import play.Logger;
import play.i18n.Messages;
import play.libs.Json;
import play.libs.F.Promise;
import play.mvc.Result;

import com.fasterxml.jackson.databind.JsonNode;
import com.xo.web.core.XODAOException;
import com.xo.web.core.XOException;
import com.xo.web.mgr.XoClientJobConfigLogic;
import com.xo.web.models.system.ClientJobsConfigurationsId;
import com.xo.web.models.system.PermissionEnum;
import com.xo.web.models.system.XoClientJobConfig;
import com.xo.web.security.authorization.action.Authroize;
import com.xo.web.util.XoPromiseCall;
import com.xo.web.util.XoUtil;
import com.xo.web.viewdtos.MessageDto;
import com.xo.web.viewdtos.MessageType;
import com.xo.web.viewdtos.XoClientJobConfigDto;

@SuppressWarnings("finally")
public class XoClientJobConfigController extends BaseController<XoClientJobConfig, ClientJobsConfigurationsId> implements Configurationsl18NLabels{

	private final XoClientJobConfigLogic xoClientJobConfigLogic;

	public XoClientJobConfigController() {
		super(new XoClientJobConfigLogic());
		this.xoClientJobConfigLogic = (XoClientJobConfigLogic) entityLogic;
	}

	@Authroize(permissions = {PermissionEnum.READ_CLIENT_JOB_CONFIG})
	public Result readAll() {
		JsonNode jsonResponse = null;
		try {
			jsonResponse = Json.toJson(this.xoClientJobConfigLogic.findAllClientJobs());
		} catch (Exception e) {
			Logger.error("Error while reading the jobs list.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_UNABLE_TO_LOAD_CONFIGURATIONS));
			throw new XOException(e);
		} finally{
			return ok(jsonResponse);
		}
	}

	@Authroize(permissions = {PermissionEnum.READ_CLIENT_JOB_CONFIG})
	public Result readAll(Integer clientId) {
		JsonNode jsonResponse = null;
		try {
			if(clientId > 0) {
				jsonResponse = Json.toJson(this.xoClientJobConfigLogic.findAllClientJobs(clientId));
			} else {
				jsonResponse = generateErrorResponse(Messages.get(ERR_INVALID_CONFIGURATION_ID));
			}
		} catch (Exception e) {
			Logger.error("Error while reading the jobs  list.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_UNABLE_TO_LOAD_CONFIGURATIONS));
			throw new XOException(e);
		} finally{
			return ok(jsonResponse);
		}
	}

	@Authroize(permissions = {PermissionEnum.CREATE_CLIENT_JOB_CONFIG})
	public Result create() {
		JsonNode json = request().body().asJson();
		JsonNode jsonResponse = null;
		if (json == null) {
			Logger.error(BAD_REQUEST_UNKNOWN_DATA);
			return badRequest(generateErrorResponse(BAD_REQUEST_UNKNOWN_DATA));
		}
		try {
			if (XoUtil.jsonValidate(json, "clientId")) {
				XoClientJobConfigDto xoClientJobConfigDto = Json.fromJson(json, XoClientJobConfigDto.class);

				if(this.xoClientJobConfigLogic.isConfigExists(xoClientJobConfigDto)) {
					XoClientJobConfig xoClientJobConfig = this.xoClientJobConfigLogic.save(xoClientJobConfigDto);
					jsonResponse = generateSuccessResponse(Messages.get(MSG_CONFIGINSTANCE_CREATED_SUCCESSFULLY, xoClientJobConfig.getXoJob().getName()));
				}
				else {
					Logger.error("Json validation failed. Configuration Already exists.");
					jsonResponse = generateErrorResponse(Messages.get(ERR_JOB_ALREADY_EXIST));
				}
			} else {
				Logger.error("Json validation failed. Please check the job details json.");
				jsonResponse = generateErrorResponse(Messages.get(ERR_CONFIGINSTANCE_JSON_INVALID));
			}
		} catch (XODAOException e) {
			Logger.error("Error while saving the Job details.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_SAVING_CONFIGTEMPLATE));
			throw new XOException(e);
		} catch (XOException e) {
			Logger.error("Job has been already registered", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_CONFIG_ALREADY_REGISTERED));
			throw new XOException(e);
		} catch(Exception e) {
			Logger.error("Error while saving the Job details.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_JOB_SAVE_FAILED));
			throw new XOException(e);
		} finally {
			return ok(jsonResponse);
		}
	}

	@Authroize(permissions = {PermissionEnum.DELETE_CLIENT_JOB_CONFIG})
	public Result delete(){
		JsonNode json = request().body().asJson();
		JsonNode jsonResponse = null;
		try {
			ClientJobsConfigurationsId clientJobsConfigDto = Json.fromJson(json, ClientJobsConfigurationsId.class);
			this.xoClientJobConfigLogic.delete(clientJobsConfigDto);
			jsonResponse = generateSuccessResponse(Messages.get(MSG_CONFIG_DELETED_SUCCESSFULLY));
		} catch (Exception e) {
			Logger.error("Error while changing the Client job configuration status.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_CONFIG_DELETE_FAILED));
			throw new XOException(e);
		} finally{
			return ok(jsonResponse);
		}
	}


	@Authroize(permissions = {PermissionEnum.ACTIVATE_CLIENT_JOB_CONFIG, PermissionEnum.DEACTIVATE_CLIENT_JOB_CONFIG})
	public Result toggleActiveStatus(Integer clientId, Integer jobId, Integer configId) throws XOException {
		JsonNode jsonResponse = null;
		try {
			if(clientId > 0 && jobId > 0 && configId > 0) {
				this.xoClientJobConfigLogic.toggleActiveStatus(new ClientJobsConfigurationsId(clientId, jobId, configId));
				jsonResponse = generateSuccessResponse(Messages.get(MSG_JOB_STATUS_CHANGED_SUCCESSFULLY));
			} else {
				jsonResponse = generateErrorResponse(Messages.get(ERR_JOB_INVALID_ID));
			}
		} catch (Exception e) {
			Logger.error("Error while changing the User role status.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_JOB_STATUS_CHANGE_FAILED));
			throw new XOException(e);
		} finally{
			return ok(jsonResponse);
		}
	}

	@Authroize(permissions = {PermissionEnum.READ_CLIENT_JOB_CONFIG})
	public Promise<Result> runJob(final Integer clientId, final Integer jobId, final Integer configId){

		return new XoPromiseCall<Result>() {

			public Result process() throws Throwable {
				JsonNode jsonResponse = null;
				try {
					ClientJobsConfigurationsId clientJobsConfigurationsId = null;
					if(clientId > 0 && jobId > 0 && configId > 0) {
						clientJobsConfigurationsId = new ClientJobsConfigurationsId(clientId, jobId, configId);
						xoClientJobConfigLogic.runJobNow(clientJobsConfigurationsId);
					}
					jsonResponse = generateSuccessResponse(Messages.get(MSG_JOB_RUN_SUCCESS), 
							new MessageDto(xoClientJobConfigLogic.findLastStatusMsg(clientJobsConfigurationsId), MessageType.success));
				}catch (Exception e) {
					Logger.error("Error while Starting th Job.", e);
					jsonResponse = generateErrorResponse(Messages.get(ERR_JOB_RUN_FAILED));
					throw new XOException(e);
				} finally{
					return ok(jsonResponse);
				}
			}
		}.startProcess();
	}

}
