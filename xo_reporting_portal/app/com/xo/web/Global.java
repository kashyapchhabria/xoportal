package com.xo.web;

import com.xo.web.util.XoAppConfigKeys;
import com.xo.web.util.XoAsyncTaskHandler;
import com.xo.web.util.XoAsynchTask;
import com.xo.web.work.XoWorkerManager;

import play.Application;
import play.Configuration;
import play.GlobalSettings;
import play.Logger;
import play.api.mvc.EssentialFilter;
import play.filters.gzip.GzipFilter;
/**
 */
@SuppressWarnings("unchecked")
public class Global extends GlobalSettings {

    /**
     * Sync the context lifecycle with Play's.
     */
    @Override
    public void onStart(final Application application) {
    	XoAsyncTaskHandler.init();
    	initiateWorkerManager(application);
        super.onStart(application);
    }

	private void initiateWorkerManager(final Application application) {
		Configuration configuration = application.configuration();
		boolean active = configuration.getBoolean(XoAppConfigKeys.WORKER_MANAGER_ACTIVE);
		boolean xossoStatus = configuration.getBoolean(XoAppConfigKeys.XOSSO_STATUS);

		if(active) {
			XoAsyncTaskHandler.submitAsynchTask(new XoAsynchTask("Worker Manager job loader") {

	            public void process() throws Throwable {
	            	XoWorkerManager.getXoTaskManager().loadAvailableWorks();
	            }
	        });
		}
	}
    /**
     * Sync the context lifecycle with Play's.
     */
    @SuppressWarnings("deprecation")
	@Override
    public void onStop(final Application app) {
    	XoAsyncTaskHandler.closeAsynchHandler();
    	XoWorkerManager.getXoTaskManager().cancelAllScheduledWorks();
        super.onStop(app);
    }

	public <T extends EssentialFilter> Class<T>[] filters() {
        return new Class[]{GzipFilter.class};
    }

}
