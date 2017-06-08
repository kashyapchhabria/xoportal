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
	
	static final String IMPALA_DB = "impala.db";
	static final String IMPALA_DB_URL = "impala.url";
	
	static final String ES_HOST = "campaign.es.url";
	static final String ES_INDEX = "campaign.es.index";
	static final String ES_TYPE = "campaign.es.type";
	static final String ES_CLUSTER_NAME = "campaign.es.clusterName";
	static final String ES_ORDER = "campaign.es.csvorder";

}
