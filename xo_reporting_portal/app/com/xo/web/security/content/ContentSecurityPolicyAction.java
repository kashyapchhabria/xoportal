package com.xo.web.security.content;

import com.xo.web.util.XoAppConfigKeys;
import com.xo.web.util.XoUtil;
import play.libs.F;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

public class ContentSecurityPolicyAction extends Action<ContentSecurityPolicy> {

	/*public static final String CSP_HEADER = "default-src 'self' " + 
			XoUtil.getConfig(XoAppConfigKeys.TABLEAU_PUBLIC_HOST) +" ; connect-src 'self' " + 
			XoUtil.getConfig(XoAppConfigKeys.TABLEAU_PUBLIC_HOST) +" ;script-src 'self' 'unsafe-eval' 'unsafe-inline' " + 
			XoUtil.getConfig(XoAppConfigKeys.TABLEAU_PUBLIC_HOST) +" 'unsafe-eval' 'unsafe-inline' ; frame-src 'self' " + 
			XoUtil.getConfig(XoAppConfigKeys.TABLEAU_PUBLIC_HOST) +"; object-src 'self' " + 
			XoUtil.getConfig(XoAppConfigKeys.TABLEAU_PUBLIC_HOST) +";font-src 'self' " +
			XoUtil.getConfig(XoAppConfigKeys.TABLEAU_PUBLIC_HOST) +";style-src 'self' 'unsafe-inline' " +
			XoUtil.getConfig(XoAppConfigKeys.TABLEAU_PUBLIC_HOST) +" 'unsafe-inline' ;img-src 'self' " +
			XoUtil.getConfig(XoAppConfigKeys.TABLEAU_PUBLIC_HOST) +";media-src 'self' " +
			XoUtil.getConfig(XoAppConfigKeys.TABLEAU_PUBLIC_HOST) +";report-uri /cspreport";*/

	public static final String OFFICIAL = "Content-Security-Policy";
	public static final String MOZILLA = "X-Content-Security-Policy";
	public static final String WEBKIT = "X-WebKit-CSP";
	public static final String POLICIES = ContentSecurityPolicyAction.getPolicies();
	public static final String POLICY_ALREADY_SET = "policy-set";

	@Override
	public F.Promise<Result> call(Http.Context ctx) throws Throwable {
		if (!ctx.args.containsKey(POLICY_ALREADY_SET)) {
			ctx.args.put(POLICY_ALREADY_SET, "");
			String value = configuration.value();
			String csp = (value == null || value.isEmpty()) ? POLICIES : value;
			ctx.response().setHeader(OFFICIAL, csp);
			ctx.response().setHeader(MOZILLA, csp);
			ctx.response().setHeader(WEBKIT, csp);
		}
		return delegate.call(ctx);
	}

	private static String getPolicies() {
		/*String policy = application().configuration().getString("csp.policy");
		Logger.debug(OFFICIAL + ": " + policy);*/
		return /*CSP_HEADER*/ null;
	}
}
