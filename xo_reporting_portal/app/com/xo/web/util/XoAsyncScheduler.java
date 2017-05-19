package com.xo.web.util;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

	public final static ScheduledFuture<?> submitAsynchSchedulerTask(XoAsynchTask asynchTask,long delay,long frequency, TimeUnit timeUnit) {
		ScheduledFuture<?> taskHandle = null;
		if (asynchTask != null && asynchTask instanceof Runnable) {
			if (scheduler == null || scheduler.isShutdown()
					|| scheduler.isTerminated()) {
				init();
			}
			taskHandle = scheduler.scheduleAtFixedRate(asynchTask,delay, frequency, timeUnit);
		}
		return taskHandle;
	}

}
