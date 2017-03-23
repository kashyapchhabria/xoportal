package com.xo.web.util;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.xo.web.persistence.XODBTransaction;

import play.Logger;

public final class XoAsyncScheduler {

	public static  ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1000);	

	public final static void init() {
		closeAsynchScheduler();
		Logger.info("Initializing the asynchronous thread workers...");
		scheduler = Executors.newScheduledThreadPool(1000);	
		Logger.info("Asynchronous scheduler thread workers initialized successfully.");
	}

	public final static void closeAsynchScheduler() {
		Logger.info("Shutting down the asynch thread workers...");
		if (scheduler != null) {
			scheduler.shutdown();
		}
		 try {
			 scheduler.awaitTermination(1, TimeUnit.DAYS);
	        } catch (InterruptedException ex) {
	        	Logger.error("Could not cancel executor service.");
	        }
		scheduler = null;
		Logger.info("Asynch worker threads are closed successfully.");
	}

	public final static void submitAsynchSchedulerTask(XoAsynchTask asynchTask,long delay,long frequency, TimeUnit timeUnit) {
		if (asynchTask != null && asynchTask instanceof Runnable) {
			if (scheduler == null || scheduler.isShutdown()
					|| scheduler.isTerminated()) {
				init();
			}
			final ScheduledFuture<?> taskHandle = scheduler.scheduleAtFixedRate(
			        new Runnable() {
			        	@XODBTransaction
			            public void run() {
			                try {
			                	XoAsyncTaskHandler.submitAsynchTask(asynchTask);
			                }catch(Exception ex) {
			                    ex.printStackTrace(); //or loggger would be better
			                }
			            }
			        },delay, frequency, timeUnit);
		}
	}

}
