package com.xo.web.security.authorization.action;

import com.xo.web.ext.tableau.mgr.TableauObjectLogic;
import com.xo.web.models.system.PermissionEnum;
import com.xo.web.security.authorization.SecureResourceHandler;
import com.xo.web.security.authorization.persistence.SecureRowLevelHandler;
import com.xo.web.util.XoUtil;

import play.libs.F.Promise;
import play.mvc.Action;
import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Results;

/**
 * @author maria sekar
 */
public class XOAuthroizationAction extends Action<Authroize>
{

	private static final String TABLEAU_CORS = "Tableau CORS";
	private static final String OFFICIAL = "Content-Security-Policy";
	private static final String MOZILLA = "X-Content-Security-Policy";
	private static final String WEBKIT = "X-WebKit-CSP";
	private static final String POLICY_ALREADY_SET = "policy-set";

	@Override
	public Promise<Result> call(Context ctx) throws Throwable {

		PermissionEnum[] permissions = configuration.permissions();
		
		String value = configuration.value();
		
		String meta = configuration.meta();

		SecureResourceHandler dynamicResourceHanlder = new SecureResourceHandler();
		SecureRowLevelHandler secureRowLevelHandler = new SecureRowLevelHandler(ctx, permissions);

		//String content = configuration.content();

		Promise<Result> result = null;
		if (XoUtil.isNotNull(value, meta) && dynamicResourceHanlder.isAllowed(value, meta, ctx)) {
			result = delegate.call(ctx);
		} else if(dynamicResourceHanlder.isAllowed(ctx, permissions)) {
			if(XoUtil.isNotNull(meta) && TABLEAU_CORS.equalsIgnoreCase(meta)) {
				appendCORSHeaders(ctx);
			}
			result = secureRowLevelHandler.enableAndInjectResourceFilters(delegate);
		} else {
			ctx.response().setContentType(XoUtil.CONTENT_TYPE_JSON);
			result=Promise.pure((Result)Results.redirect(com.xo.web.controllers.routes.UserController.jsonAccessDenied()));
		}
		return result;
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
