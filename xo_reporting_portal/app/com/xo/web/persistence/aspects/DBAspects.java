package com.xo.web.persistence.aspects;

import com.xo.web.models.dao.JPABaseDAO;
import com.xo.web.persistence.JPAUtil;
import com.xo.web.security.authorization.action.Authroize;
import com.xo.web.security.authorization.action.XOAuthroizationAction;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import play.mvc.Http.Context;
import play.mvc.Result;

import javax.persistence.EntityManager;
import java.lang.reflect.Method;

/**
 * Aspects specific to db related concerns
 * @author sekar
 *
 */
@Aspect
public class DBAspects {

	private static final Logger LOGGER = LoggerFactory.getLogger(DBAspects.class);
	private static final String READONLY_CONNECTION = "org.hibernate.readOnly";

	/**
	 * Injecting entity manager before calling the em() method of JPABaseDAO. 
	 * @param joinPoint
	 * @param bd
	 */
	@Before("call(* com.xo.web.models.dao.JPABaseDAO+.em(..)) && this(bd)")
	public void injectEntityManager(JoinPoint joinPoint, JPABaseDAO bd) {
		bd.setEntityManager(JPAUtil.em());
		//LOGGER.info("Injected enitymanager to : " + joinPoint.getSignature().getName());
	}

	/**
	 * Pointcuts to get the XODBTransaction methods
	 */
	@Pointcut("execution(@com.xo.web.persistence.XODBTransaction * *(..)) || call(public play.mvc.Result com.xo.web.controllers.*.*(..)) || call(public play.mvc.Result com.xo.web.ext.*.controllers.*.*(..))")
	public void getTransactionMethods(){

	}

	/**
	 * Pointcuts to get the XODBTransaction methods
	 */
	@Pointcut("execution(@com.xo.web.persistence.XODBReadOnly * *(..))")
	public void getReadOnlyTransactionMethods(){

	}

	/**
	 * Processing the transactions based on the XODBTransaction annotation.
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("getTransactionMethods()")
	public Object handleTransaction(ProceedingJoinPoint joinPoint) {
		Object resultObject = null;
		EntityManager entityManager = JPAUtil.em();
		try{
			if(entityManager != null) {
				javax.persistence.EntityTransaction transaction = entityManager.getTransaction();
				try{
					final String callerName = joinPoint.getSignature().getName();
					if(transaction != null && !transaction.isActive()) {
						transaction.begin();
						LOGGER.info("Transaction started for : " + callerName);
					}
					if(this.isAuthorizedControllerMethod(joinPoint)) {
						LOGGER.info("Entity Manager For this thread : " + entityManager);
						resultObject = new XOAuthroizationAction().call(joinPoint);
					} else {
						resultObject = joinPoint.proceed();
					}
					if(transaction != null && transaction.isActive()) {
						transaction.commit();
						LOGGER.info("Transaction ended for : " + callerName);
					}
				}catch(Throwable th) {
					if(transaction != null && transaction.isActive()) {
						transaction.rollback();
					}
					LOGGER.info("Error while performing CUD operation...", th);
				}
			}
		} catch(Throwable th) {
			LOGGER.info("Error occurred while processing the request.", th); 
		} finally {
			JPAUtil.closeEM();
		}
		Signature sig = joinPoint.getSignature();
		if (sig instanceof MethodSignature) {
			Method method = ((MethodSignature) sig).getMethod();
			if(method.getReturnType() == Result.class) {
				Context.current().session().clear();
			}
		}
		return resultObject;
	}

	private final boolean isAuthorizedControllerMethod(ProceedingJoinPoint joinPoint) {
		Signature sig = joinPoint.getSignature();
		if (sig instanceof MethodSignature) {
			Method method = ((MethodSignature) sig).getMethod();
			return method.isAnnotationPresent(Authroize.class);
		}
		return false;
	}
	
	/**
	 * Processing the readonly transactions based on the XODBTransaction annotation.
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("getReadOnlyTransactionMethods()")
	public Object handleReadonlyTransaction(ProceedingJoinPoint joinPoint) {
		Object resultObject = null;
		EntityManager entityManager = JPAUtil.em();
		try{
			if(entityManager != null) {
				Boolean prevState = (Boolean) entityManager.getProperties().get(READONLY_CONNECTION);
				entityManager.setProperty(READONLY_CONNECTION, true);
				javax.persistence.EntityTransaction transaction = entityManager.getTransaction();
				try{
					final String callerName = joinPoint.getSignature().getName();
					if(transaction != null && !transaction.isActive()) {
						transaction.begin();
						LOGGER.info("Readonly transaction started for : " + callerName);
					}
					resultObject = joinPoint.proceed();
					if(transaction != null && transaction.isActive()) {
						transaction.commit();
						LOGGER.info("Readonly transaction ended for : " + callerName);
					}
				}catch(Throwable th) {
					if(transaction != null && transaction.isActive()) {
						transaction.rollback();
					}
					LOGGER.info("Error while performing read only operation...", th);
				}
				entityManager.setProperty(READONLY_CONNECTION, prevState);
			}
		} catch(Throwable th) {
			LOGGER.info("Error occurred while processing the readonly function.", th); 
		} finally {
			JPAUtil.closeEM();
		}
		return resultObject;
	}
}
