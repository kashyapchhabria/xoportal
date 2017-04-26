package com.xo.web.work.scheduler;

import com.fasterxml.jackson.databind.JsonNode;
import com.xo.web.core.XOException;
import com.xo.web.mgr.TokenActionLogic;
import com.xo.web.viewdtos.XoClientJobConfigDto;
import com.xo.web.work.XoWorker;

import play.Logger;
import play.libs.Json;

public final class XoSessionKiller extends XoWorker {
	
	public XoSessionKiller(XoClientJobConfigDto clientJobsConfigDto) {
		super(clientJobsConfigDto);
		Logger.info("XoSessionKiller worker loaded successfully.");
	}

	public void process() throws Exception{
		Logger.debug("XoSessionKiller is checking & deleting the expired tokens...");
		new TokenActionLogic().deleteAllExpiredTokens();
	}

}
