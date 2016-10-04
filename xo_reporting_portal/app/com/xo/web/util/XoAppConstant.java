package com.xo.web.util;

import com.feth.play.module.mail.Mailer;

public interface XoAppConstant {

	public static final String STRING_UTC = "UTC";

	// Server related constants
	public static final String CONTENT_TYPE_JSON = "application/json";
	public static final Mailer XO_MAILER = Mailer.getDefaultMailer();

	// HTTP header constants
	public static final String HEADER_AUTH_TOKEN = "X-Xoanon-Auth";
	public static final String HEADER_X_FORWARDED_FOR = "X-Forwarded-For";
	public static final String HEADER_X_SUPER_CLIENT = "X-Super-Client";

	// Security related constants
	public static final String CURRENT_PAGE = "currentPage";
	public static final String AUTH_TOKEN_QUERY_PARAM = "authToken";
	public static final String AUTH_TOKEN_PASSWORD_RESET = "PASSWORD_RESET_TOKEN";

	// DB Related constants
	public static final int PREF_DEFAULT_DB_FETCH_LIMIT = 2000;

	// Formatting related contants
	public static final String DATE_FORMAT_DD_MM_YYYY = "dd-MM-yyyy";
	public static final String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";

	public static final String STRING_UTF_8 = "UTF-8";
}
