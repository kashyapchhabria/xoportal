package com.xo.web.util;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xo.web.persistence.XODBTransaction;

public final class XoAsyncScheduler {

	public static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(20);
	private static final Logger LOGGER = LoggerFactory.getLogger(XoAsyncScheduler.class);

	public final static void init() {
		closeAsynchScheduler();
		scheduler = Executors.newScheduledThreadPool(20);
		LOGGER.info("Asynchronous scheduler thread workers initialized successfully.");
	}

	public final static void closeAsynchScheduler() {
		LOGGER.info("Shutting down the asynch thread workers...");
		if (scheduler != null) {
			scheduler.shutdown();
		}
		 try {
			 scheduler.awaitTermination(1, TimeUnit.DAYS);
	        } catch (InterruptedException ex) {
	        	LOGGER.error("Could not cancel executor service.");
	        }
		scheduler = null;
		LOGGER.info("Asynch worker threads are closed successfully.");
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
			                	LOGGER.error("Error while exuecuting the task.", ex);
			                }
			            }
			        },delay, frequency, timeUnit);
		}
	}

}
