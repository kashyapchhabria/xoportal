package com.xo.web.work.scheduler;

import com.fasterxml.jackson.databind.JsonNode;
import com.xo.web.core.XOException;
import com.xo.web.ext.tableau.TableauConnector;
import com.xo.web.viewdtos.XoClientJobConfigDto;

import play.Logger;
import play.libs.Json;

public final class XoTableauReportSynchronizer extends XoScheduler {

	private static final String CONFIG_TABLEAU_SYNC = "Tableau Sync";
	private static final String CONFIG_TABLEAU_SYNC_PARAMETER_INITIALDELAY = "initialdelay";
	private static final String CONFIG_TABLEAU_SYNC_PARAMETER_FREQUENCY = "frequency";

	public XoTableauReportSynchronizer(XoClientJobConfigDto clientJobsConfigDto) {
		super(clientJobsConfigDto);
		Logger.info("XoTableauReportSynchronizer worker loaded successfully.");
	}

	public void process() throws Exception {
		Logger.info("XoTableauReportSynchronizer is synching the tableau reports...");
		(new TableauConnector()).syncPortalData(this.xoClientJobConfigDto.configJson);;
		Logger.info("Successfully synched tableau reports.");
	}

	protected void setConfigs() throws XOException {
		try {
			JsonNode obj = Json.parse(this.xoClientJobConfigDto.configJson);
			this.initialDelay = obj.findPath(CONFIG_TABLEAU_SYNC_PARAMETER_INITIALDELAY).asInt();
			this.frequency = obj.findPath(CONFIG_TABLEAU_SYNC_PARAMETER_FREQUENCY).asInt();
		} catch (Exception e) {
			Logger.error(CONFIG_TABLEAU_SYNC + " Failed to set the configurations.");
			throw new XOException(e);
		}
	}

}
