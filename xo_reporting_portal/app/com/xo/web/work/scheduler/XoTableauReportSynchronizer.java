package com.xo.web.work.scheduler;

import com.fasterxml.jackson.databind.JsonNode;
import com.xo.web.core.XOException;
import com.xo.web.ext.tableau.TableauConnector;
import com.xo.web.viewdtos.XoClientJobConfigDto;
import com.xo.web.work.XoWorker;

import play.Logger;
import play.libs.Json;

public final class XoTableauReportSynchronizer extends XoWorker {

	public XoTableauReportSynchronizer(XoClientJobConfigDto clientJobsConfigDto) {
		super(clientJobsConfigDto);
		Logger.info("XoTableauReportSynchronizer worker loaded successfully.");
	}

	public void process() throws Exception {
		Logger.info("XoTableauReportSynchronizer is synching the tableau reports...");
		(new TableauConnector()).syncPortalData(this.xoClientJobConfigDto.configJson);;
		Logger.info("Successfully synched tableau reports.");
	}	

}
