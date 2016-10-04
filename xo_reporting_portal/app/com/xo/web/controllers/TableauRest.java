package com.xo.web.controllers;

import com.xo.web.ext.tableau.mgr.TableauObjectLogic;
import com.xo.web.util.XoPromiseCall;

import play.Logger;
import play.libs.F.Promise;
import play.mvc.Result;

public class TableauRest extends XOBaseController {

	public Promise<Result> syncTableauReports() {

		return new XoPromiseCall<Result>() {

			public Result process() throws Throwable {
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
		}.startProcess();
	}

}
