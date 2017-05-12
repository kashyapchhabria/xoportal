package com.xo.web;

import java.io.IOException;

import com.xo.web.akka.xoactors.XoClientSyncActor;
import com.xo.web.core.XOException;

import com.xo.web.util.XoAppConfigKeys;
import com.xo.web.util.XoAsyncScheduler;
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
	
	private boolean xossoStatus;
	private boolean active;
	private XoClientSyncActor xoClientSyncActor;
	
	public Global() throws IOException, XOException {
		super();
	}
    /**
     * Sync the context lifecycle with Play's.
     */
    @Override
    public void onStart(final Application application) {
    	Configuration configuration = application.configuration();
    	XoAsyncTaskHandler.init();
    	XoAsyncScheduler.init();
    	this.active = configuration.getBoolean(XoAppConfigKeys.WORKER_MANAGER_ACTIVE);
		this.xossoStatus = configuration.getBoolean(XoAppConfigKeys.XOSSO_STATUS);
		this.initiateWorkerManager(application);
        super.onStart(application);
    }

	private void initiateWorkerManager(final Application application) {
		if(active) {
			XoAsyncTaskHandler.submitAsynchTask(new XoAsynchTask("Worker Manager job loader") {

	            public void process() throws Throwable {
	            	XoWorkerManager.getXoTaskManager().loadAvailableWorks();
	            }
	        });
		}

		if(xossoStatus){
			try {
				this.xoClientSyncActor = new XoClientSyncActor();
			} catch (IOException | XOException e) {
				Logger.error("Error while initiating the client sync.", e);
			}
			XoAsyncTaskHandler.submitAsynchTask(new XoAsynchTask("Xosso Actor system loader") {
	            public void process() throws Throwable {
	            	xoClientSyncActor.startSyncProcess();
	            	Logger.info("Xosso Actor system registered successfully.");
	            }
	        });
		}
	}

	@Override
    public void onStop(final Application app) {
    	if(active) {
    		XoAsyncTaskHandler.closeAsynchHandler();
    		XoAsyncScheduler.closeAsynchScheduler();
    	}
    	if(xossoStatus){
			try {
				xoClientSyncActor.closeClientSyncSockets();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
        super.onStop(app);
    }

	public <T extends EssentialFilter> Class<T>[] filters() {
        return new Class[]{GzipFilter.class};
    }

}
