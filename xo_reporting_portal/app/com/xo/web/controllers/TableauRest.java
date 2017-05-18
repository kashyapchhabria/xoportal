package com.xo.web.controllers;

import com.xo.web.ext.tableau.mgr.TableauObjectLogic;

import play.Logger;
import play.mvc.Result;

public class TableauRest extends XOBaseController {

	public Result syncTableauReports() {

		String status = "Successfully updated the tableau reports in portal.";
		try{
			TableauObjectLogic tableauObjectLogic = new TableauObjectLogic();
			tableauObjectLogic.syncTableauReports(getCurrentRestUser().getXoClient());
		}catch(Exception e) {
			status = "Error occurred. Please send the error report to DEV team for more information. \n \n " + e.getMessage();
			Logger.error("Tableau synch error.", e);
		}
		return ok(status);
	}

}
