package com.xo.web.controllers;

public interface UserI18NLabels {

	public static final String ERR_USER_STATUS_CHANGE_FAILED = "user.save.error1";
	public static final String MSG_USER_STATUS_CHANGED_SUCCESSFULLY = "user.save.success2";
	public static final String ERR_USER_STATUS_INVALID_ID = "user.save.error5";
	public static final String ERR_UNABLE_TO_LOAD_USERS = "user.read.error1";
	public static final String MSG_USER_UPDATE_SUCCESS = "user.save.success3";
	public static final String ERR_SAVING_USER = "user.save.error2";
	public static final String ERR_EMAIL_ALREADY_REGISTERED = "user.save.error3";
	public static final String ERR_USER_JSON_INVALID = "user.save.error4";
	public static final String CREATED_SUCCESSFULLY = "user.save.success4";
	public static final String MSG_FORGOT_PASS_MAIL_SENT_SUCCESS = "user.forgotpass.success1";
	public static final String ERR_INVALID_USER_ID = "user.read.error2";
	public static final String MSG_RESET_MAIL_SENT_SUCCESS = "user.reset.success1";
	public static final String MSG_PASS_UPDATE_SUCCESS = "user.save.success1";
	public static final String ERR_MAIL_NOT_FOUND = "user.read.error3";
	public static final String ERR_EMAIL_REQUIRED = "user.read.error4";
	public static final String ERR_CHECK_MAIL_PASS_AND_ACTIVE = "user.read.error5";
	public static final String ERR_EMAIL_NOT_VERIFIED = "user.read.error6";
	public static final String ERR_LINK_EXPIRED = "user.read.error7";
	public static final String ERR_REPEAT_PASS_MATCH = "user.read.error8";
	public static final String ERR_PASS_REQUIRED = "user.read.error9";
	public static final String ERR_ACCESS_DENIED = "user.read.error10";

	// User Upload related labels
	public static final String PLEASE_CHECK_THE_UPLOADED_FILE = "user.upload.error4";
	public static final String ERROR_WHILE_READING_THE_FILE_CONTENT = "user.upload.error3";
	public static final String ERROR_WHILE_PARSING_USERS_LISTS = "user.upload.error2";
	public static final String ERROR_WHILE_PARSING_USERS_FILE = "user.upload.error1";
	public static final String UPLOAD_SUCCESSFULLY = "user.upload.sucess1";
	
	public static final String EMAIL_TEMPLATE_HTML_ACCOUNT_VERIFY = "com.xo.web.views.html.email.verify_email";
	public static final String EMAIL_TEMPLATE_HTML_PASSWORD_RESET = "com.xo.web.views.html.email.password_reset";

}