package com.xo.web.security.authorization.action;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;

import com.xo.web.ext.tableau.mgr.TableauObjectLogic;
import com.xo.web.models.system.PermissionEnum;
import com.xo.web.security.authorization.SecureResourceHandler;
import com.xo.web.security.authorization.persistence.SecureRowLevelHandler;
import com.xo.web.util.XoUtil;

import play.mvc.Http.Context;
import play.mvc.Results;

/**
 * @author maria sekar
 */
public class XOAuthroizationAction
{

	private static final String TABLEAU_CORS = "Tableau CORS";
	private static final String OFFICIAL = "Content-Security-Policy";
	private static final String MOZILLA = "X-Content-Security-Policy";
	private static final String WEBKIT = "X-WebKit-CSP";
	private static final String POLICY_ALREADY_SET = "policy-set";

	public Object call(ProceedingJoinPoint joinPoint) throws Throwable {

		Context ctx = Context.current();
		Authroize authroizationConfig = this.getAuthroizationValues(joinPoint);
		PermissionEnum[] permissions = authroizationConfig.permissions();

		String value = authroizationConfig.value();

		String meta = authroizationConfig.meta();

		SecureResourceHandler dynamicResourceHanlder = new SecureResourceHandler();
		SecureRowLevelHandler secureRowLevelHandler = new SecureRowLevelHandler(permissions);

		//String content = configuration.content();

		Object resultObject = null;
		if (XoUtil.isNotNull(value, meta) && dynamicResourceHanlder.isAllowed(value, meta, ctx)) {
			resultObject = joinPoint.proceed();
		} else if(dynamicResourceHanlder.isAllowed(ctx, permissions)) {
			if(XoUtil.isNotNull(meta) && TABLEAU_CORS.equalsIgnoreCase(meta)) {
				appendCORSHeaders(ctx);
			}
			resultObject = secureRowLevelHandler.enableAndInjectResourceFilters(joinPoint);
		} else {
			ctx.response().setContentType(XoUtil.CONTENT_TYPE_JSON);
			resultObject = Results.redirect(com.xo.web.controllers.routes.UserController.jsonAccessDenied());
		}
		return resultObject;
	}

	private Authroize getAuthroizationValues(final ProceedingJoinPoint joinPoint) {
		Signature sig = joinPoint.getSignature();
		Authroize authroize = null;
		if (sig instanceof MethodSignature) {
			Method method = ((MethodSignature) sig).getMethod();
			if(method.isAnnotationPresent(Authroize.class)) {
				authroize = method.getAnnotation(Authroize.class);
			}
		}
		return authroize;
	}

	private void appendCORSHeaders(Context ctx) {
		if (!ctx.args.containsKey(POLICY_ALREADY_SET)) {
			ctx.args.put(POLICY_ALREADY_SET, "");
			String csp = this.buildCORSHeaders();
			ctx.response().setHeader(OFFICIAL, csp);
			ctx.response().setHeader(MOZILLA, csp);
			ctx.response().setHeader(WEBKIT, csp);
		}
	}

	private String buildCORSHeaders() {
		String tableauHost = null;
		try {
			TableauObjectLogic tableauObjectLogic = new TableauObjectLogic();
			tableauHost = tableauObjectLogic.tableauPublicHost;
		} catch(Exception e) {
			System.out.println("Error while loading the tableau config.");
		}

		StringBuilder CSP_HEADER = new StringBuilder("default-src 'self' "); 
		CSP_HEADER.append(tableauHost);
		CSP_HEADER.append(" ; connect-src 'self' "); 
		CSP_HEADER.append(tableauHost);
		CSP_HEADER.append(" ;script-src 'self' 'unsafe-eval' 'unsafe-inline' ");
		CSP_HEADER.append(tableauHost);
		CSP_HEADER.append(" 'unsafe-eval' 'unsafe-inline' ; frame-src 'self' ");
		CSP_HEADER.append(tableauHost);
		CSP_HEADER.append("; object-src 'self' ");
		CSP_HEADER.append(tableauHost);
		CSP_HEADER.append(";font-src 'self' ");
		CSP_HEADER.append(tableauHost);
		CSP_HEADER.append(";style-src 'self' 'unsafe-inline' ");
		CSP_HEADER.append(tableauHost);
		CSP_HEADER.append(" 'unsafe-inline' ;img-src 'self' ");
		CSP_HEADER.append(tableauHost);
		CSP_HEADER.append(";media-src 'self' ");
		CSP_HEADER.append(tableauHost);
		CSP_HEADER.append(";report-uri /cspreport");
		CSP_HEADER.append(";xhr-src 'self' ");
		CSP_HEADER.append(tableauHost);
		return CSP_HEADER.toString();
	}
}
