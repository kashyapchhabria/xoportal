package com.xo.web;

import com.xo.web.akka.xoactors.AppActors;
import com.xo.web.akka.xoactors.LocalSyncActor;
import com.xo.web.akka.xoactors.XoClientSyncActor;
import com.xo.web.util.XoAppConfigKeys;
import com.xo.web.util.XoAsyncTaskHandler;
import com.xo.web.util.XoAsynchTask;
import com.xo.web.work.XoWorkerManager;

import akka.actor.Props;
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
		if(xossoStatus) {
			XoAsyncTaskHandler.submitAsynchTask(new XoAsynchTask("Xoportal Actor system loader") {
				
				public void process() throws Throwable {
					registerActorSytemWorkers(application);
					Logger.info("Xoportal Actor system registered successfully.");
				}
			});
		}
	}

	private void registerActorSytemWorkers(final Application application) {
		// fetch configs
		Configuration configuration = application.configuration();
		Configuration remoteConfig = configuration.getConfig("xoportal").getConfig("akka").getConfig("remote").getConfig("netty.tcp");
		String actorHost = remoteConfig.getString("hostname");
		int actorPort = remoteConfig.getInt("port");
		String workerName = "clientsync";

		String actorPath = "akka.tcp://" + "xoportalsystem" + "@" + actorHost + ":" + actorPort + "/user/" + workerName;
		Logger.info(actorPath); // here you know what your actor's path is, well, just for show, don't do this sort of thing your code.
		AppActors.XOPORTAL_ACTOR_SYSTEM.actorOf(Props.create(XoClientSyncActor.class), workerName);
		AppActors.XOPORTAL_ACTOR_SYSTEM.actorOf(Props.create(LocalSyncActor.class), "localsyncactor");
	}

    /**
     * Sync the context lifecycle with Play's.
     */
    @SuppressWarnings("deprecation")
	@Override
    public void onStop(final Application app) {
    	XoAsyncTaskHandler.closeAsynchHandler();
    	XoWorkerManager.getXoTaskManager().cancelAllScheduledWorks();
    	if(AppActors.XOPORTAL_ACTOR_SYSTEM != null && !AppActors.XOPORTAL_ACTOR_SYSTEM.isTerminated()) {
    		AppActors.XOPORTAL_ACTOR_SYSTEM.shutdown();
    	}
        super.onStop(app);
    }

	public <T extends EssentialFilter> Class<T>[] filters() {
        return new Class[]{GzipFilter.class};
    }

}
