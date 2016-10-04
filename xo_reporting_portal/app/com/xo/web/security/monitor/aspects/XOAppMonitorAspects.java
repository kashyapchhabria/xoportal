package com.xo.web.security.monitor.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import play.Logger;

@Aspect
public class XOAppMonitorAspects {

	@Before("call(public play.mvc.Result com.xo.web.controllers.*.*(..))")
	public void printBefore(JoinPoint joinPoint) {
		Logger.info("Requested Page :" + joinPoint.getSignature().getName());
	}

	@After("call(public play.mvc.Result com.xo.web.controllers.*.*(..))")
	public void printAfter(JoinPoint joinPoint) {
		Logger.info("Responded Page : " + joinPoint.getSignature().getName());
	}

}
