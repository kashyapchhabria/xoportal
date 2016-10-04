package com.xo.web.work;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import play.Logger;
import akka.actor.Cancellable;

import com.xo.web.mgr.XoClientJobConfigLogic;
import com.xo.web.util.XoAsyncTaskHandler;
import com.xo.web.util.XoUtil;
import com.xo.web.viewdtos.XoClientJobConfigDto;
import com.xo.web.work.scheduler.XoScheduler;

public final class XoWorkerManager {

	private static final XoWorkerManager xoWorkerManager = new XoWorkerManager();
	private XoClientJobConfigLogic xoClientJobConfigLogic;
	private final ConcurrentHashMap<XoClientJobConfigDto, Cancellable> scheduledTasks = new ConcurrentHashMap<XoClientJobConfigDto, Cancellable>();

	public static XoWorkerManager getXoTaskManager() {
		return xoWorkerManager;
	}

	public final void loadAvailableWorks() {
		try {
			if(xoClientJobConfigLogic == null) {
				xoClientJobConfigLogic = new XoClientJobConfigLogic();
			}
			Collection<XoClientJobConfigDto> xoClientJobConfigs = this.xoClientJobConfigLogic.findAllClientJobs();
			if(XoUtil.hasData(xoClientJobConfigs)) {
				for(XoClientJobConfigDto xoClientJobConfigDto : xoClientJobConfigs) {
					final XoWorker xoWorker = this.loadWorker(xoClientJobConfigDto);
					this.scheduleWork(xoWorker);
				}
			}
		} catch (Throwable e) {
			Logger.error("Could not load available jobs.");
		}
	}

	public final XoWorker loadWorker(XoClientJobConfigDto xoClientJobConfigDto) {
		XoWorker xoWorker = null;
		if(xoClientJobConfigDto != null) {
			Class<?> cls = null;
			try {
				cls = Class.forName(xoClientJobConfigDto.jobClassName);
			} catch (ClassNotFoundException e) {
				Logger.warn("Job : '" + xoClientJobConfigDto.jobClassName + "' was not found!");
			}
			if (cls != null) {
				try {
					xoWorker = (XoWorker) cls.getConstructor(XoClientJobConfigDto.class).newInstance(xoClientJobConfigDto);
				} catch (Throwable e) {
					Logger.error("Could not initate the worker object.", e);
				}
			}
		}
		return xoWorker;
	}

	public final void executeWork(XoWorker xoWorker) {
		if(xoWorker != null) {
			XoAsyncTaskHandler.submitAsynchTask(xoWorker.getXoAsynchTask());
		}
	}

	public final void scheduleWork(XoWorker xoWorker) {
		if(xoWorker != null) {
			if(xoWorker instanceof XoScheduler) {

				// Get the configuration for this worker.
				XoClientJobConfigDto xoClientJobConfigDto = xoWorker.getXoClientJobConfigDto();
				// Cancel existing scheduled jobs.
				cancelScheduledWork(xoClientJobConfigDto);
				// Schedule the work again.
				XoScheduler xoScheduler = (XoScheduler) xoWorker;
				Cancellable cancellable = xoScheduler.scheduleMe();
				this.scheduledTasks.put(xoClientJobConfigDto, cancellable);
			}
		}
	}

	public final void cancelScheduledWork(XoClientJobConfigDto xoClientJobConfigDto) {
		Cancellable cancellable = scheduledTasks.remove(xoClientJobConfigDto);
		if(cancellable != null && !cancellable.isCancelled()) {
			cancellable.cancel();
		}
	}

	public final void cancelAllScheduledWorks() {
		try {
			if(xoClientJobConfigLogic == null) {
				xoClientJobConfigLogic = new XoClientJobConfigLogic();
			}
			Collection<XoClientJobConfigDto> xoClientJobConfigs = this.xoClientJobConfigLogic.findAllClientJobs();
			if(XoUtil.hasData(xoClientJobConfigs)) {
				for(XoClientJobConfigDto xoClientJobConfigDto : xoClientJobConfigs) {
					this.cancelScheduledWork(xoClientJobConfigDto);
				}
			}
		} catch (Throwable e) {
			Logger.error("Could not cancel scheduled jobs.");
		}
	}
}
