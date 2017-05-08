package com.xo.web.util;

public interface XoAppConfigKeys extends TableauConstants {

	// Application related core config keys
	static final String APPLICATION_CONTEXT = "application.context";
	static final String XO_END_USER = "xo.enduser";
	static final String APP_SECRET = "application.secret";
	static final String WORKER_MANAGER_ACTIVE = "workermanager.active";
	static final String XOSSO_URL = "xosso.url";
	static final String XOSSO_URL_LOGOUT = "xosso.urls.logout";
    static final String XOSSO_URL_APPLOGIN = "xosso.urls.applogin";
    static final String XOSSO_URL_VERIFY_TOKEN = "xosso.urls.tokenverify";
	static final String XOSSO_STATUS = "xosso.enable";
	static final String XOSSO_HOSTNAME = "xosso.hostname";
	static final String XOSSO_PORT = "xosso.port";
	
	static final String XOPORTAL_HOSTNAME = "xoportal.hostname";
	static final String XOPORTAL_PORT = "xoportal.port";
	
	static final String XOACTORS_USERSYNC = "xoactors.usersync";
	static final String EMAIL_LIST = "email.list";
}
