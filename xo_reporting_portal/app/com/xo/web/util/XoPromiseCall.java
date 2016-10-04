package com.xo.web.util;

import com.xo.web.persistence.XODBTransaction;
import play.libs.F.Function0;
import play.libs.F.Promise;

import static play.libs.F.Promise.promise;

public abstract class XoPromiseCall<T> implements Function0<T> {

	@XODBTransaction
	public final T apply() throws Throwable {
		return this.process();
	}

	public final Promise<T> startProcess() {
		return promise(this);
	}

	public abstract T process() throws Throwable;
}
