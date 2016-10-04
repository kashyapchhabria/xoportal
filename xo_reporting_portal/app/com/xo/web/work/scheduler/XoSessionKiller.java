package com.xo.web.work.scheduler;

import play.Logger;
import play.libs.Json;

import com.fasterxml.jackson.databind.JsonNode;
import com.xo.web.core.XOException;
import com.xo.web.mgr.TokenActionLogic;
import com.xo.web.viewdtos.XoClientJobConfigDto;

public final class XoSessionKiller extends XoScheduler {

	private static final String CONFIG_XO_SESSION_KILLER = "XoSession Killer";
	private static final String CONFIG_XO_SESSION_KILLER_PARAMETER_INITIALDELAY = "initialdelay";
	private static final String CONFIG_XO_SESSION_KILLER_PARAMETER_FREQUENCY = "frequency";

	public XoSessionKiller(XoClientJobConfigDto clientJobsConfigDto) {
		super(clientJobsConfigDto);
		Logger.info("XoSessionKiller worker loaded successfully.");
	}

	public void process() throws Exception{
		Logger.debug("XoSessionKiller is checking & deleting the expired tokens...");
		new TokenActionLogic().deleteAllExpiredTokens();
	}

	@Override
	protected void setConfigs() throws XOException {
		try {
			JsonNode obj = Json.parse(this.xoClientJobConfigDto.configJson);
			this.initialDelay = obj.findPath(CONFIG_XO_SESSION_KILLER_PARAMETER_INITIALDELAY).asInt();
			this.frequency = obj.findPath(CONFIG_XO_SESSION_KILLER_PARAMETER_FREQUENCY).asInt();
		} catch (Exception e) {
			Logger.error(CONFIG_XO_SESSION_KILLER + " Failed to set the configurations.");
			throw new XOException(e);
		}

	}
}
