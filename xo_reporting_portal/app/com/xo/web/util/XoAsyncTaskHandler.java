package com.xo.web.util;

import play.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class XoAsyncTaskHandler {

	private static ExecutorService EXECUTORSERVICE = Executors.newCachedThreadPool();

	public final static void init() {
		closeAsynchHandler();
		Logger.info("Initializing the asynchronous thread workers...");
		EXECUTORSERVICE = Executors.newCachedThreadPool();
		Logger.info("Asynchronous thread workers initialized successfully.");
	}

	public final static void closeAsynchHandler() {
		Logger.info("Shutting down the asynch thread workers...");
		if (EXECUTORSERVICE != null) {
			EXECUTORSERVICE.shutdown();
		}
		EXECUTORSERVICE = null;
		Logger.info("Asynch worker threads are closed successfully.");
	}

	public final static void submitAsynchTask(XoAsynchTask asynchTask) {
		if (asynchTask != null && asynchTask instanceof Runnable) {
			if (EXECUTORSERVICE == null || EXECUTORSERVICE.isShutdown()
					|| EXECUTORSERVICE.isTerminated()) {
				init();
			}
			EXECUTORSERVICE.execute(asynchTask);
		}
	}

}
