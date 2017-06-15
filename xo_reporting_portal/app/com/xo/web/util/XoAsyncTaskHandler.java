package com.xo.web.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class XoAsyncTaskHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(XoAsyncTaskHandler.class);
	private static ExecutorService EXECUTORSERVICE = Executors.newCachedThreadPool();

	public final static void init() {
		closeAsynchHandler();
		LOGGER.info("Initializing the asynchronous thread workers...");
		EXECUTORSERVICE = Executors.newCachedThreadPool();
		LOGGER.info("Asynchronous thread workers initialized successfully.");
	}

	public final static void closeAsynchHandler() {
		LOGGER.info("Shutting down the asynch thread workers...");
		if (EXECUTORSERVICE != null) {
			EXECUTORSERVICE.shutdown();
		}
		EXECUTORSERVICE = null;
		LOGGER.info("Asynch worker threads are closed successfully.");
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
