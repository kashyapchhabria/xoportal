package com.xo.web.util;

import com.xo.web.persistence.XODBTransaction;
import play.Logger;

public abstract class XoAsynchTask implements Runnable {

	private final String taskName;

	public XoAsynchTask(String taskName) {
		this.taskName = taskName;
	}

	@XODBTransaction
	public final void run() {
		try {
			process();
		} catch (Throwable e) {
			Logger.error("Error occurred while processing the asynch task :" + this.taskName, e);
		}
	}

	public abstract void process() throws Throwable ;
}
