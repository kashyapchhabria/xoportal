package com.xo.web.work.scheduler;

import java.util.concurrent.TimeUnit;

import play.Logger;
import play.libs.Akka;
import scala.concurrent.duration.Duration;
import akka.actor.Cancellable;

import com.xo.web.viewdtos.XoClientJobConfigDto;
import com.xo.web.work.XoWorker;

public abstract class XoScheduler extends XoWorker  {

	public XoScheduler(XoClientJobConfigDto clientJobsConfigDto) {
		super(clientJobsConfigDto);
	}

	protected Cancellable cancellable;
	protected int initialDelay = 0 ;
	protected int frequency = 1;

	public Cancellable scheduleMe() {
		try {
			this.setConfigs();
		} catch (Exception e) {
			Logger.error("Error while loading the configurations.", e);
		}
		this.cancellable = Akka.system().scheduler().schedule(
				Duration.create(initialDelay, TimeUnit.HOURS),
				Duration.create(frequency, TimeUnit.HOURS),
				this, Akka.system().dispatcher()
		);
		return this.cancellable;
	}

	public void finalize() throws Throwable {
		super.finalize();
		if(this.cancellable != null) {
			if(!this.cancellable.isCancelled()) {
				this.cancellable.cancel();
			}
		}
	}

}
