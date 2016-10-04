package com.xo.web.controllers;

public interface Configurationsl18NLabels {

	// Config related msg keys

    public static final String ERR_UNABLE_TO_LOAD_CONFIGURATIONS = "config.read.error1";
    public static final String ERR_INVALID_CONFIGURATION_ID = "config.read.error2";
    public static final String ERR_CONFIG_DELETE_FAILED = "config.save.error3";
    public static final String ERR_CONFIG_CHANGE_FAILED = "config.save.error4";
    public static final String MSG_CONFIG_DELETED_SUCCESSFULLY= "config.save.success1";
    public static final String MSG_CONFIG_STATUS_CHANGED_SUCCESSFULLY = "configtemp.save.success2";
    public static final String ERR_CONFIG_ALREADY_REGISTERED = "config.save.error6";

	// Config template related msg keys

    public static final String ERR_SAVING_CONFIGTEMPLATE = "configtemp.save.error5";
    public static final String ERR_CONFIGTEMPLATE_JSON_INVALID = "configtemp.save.error7";
    public static final String MSG_CONFIGTEMPLATE_CREATED_SUCCESSFULLY = "configtemp.save.success3";
    public static final String MSG_CONFIGTEMPLATE_UPDATE_SUCCESS = "configtemp.save.success4";
    
	// Config instance related msg keys

    public static final String ERR_SAVING_CONFIGINSTANCE = "configinstance.save.error8";
    public static final String ERR_CONFIGINSTANCE_JSON_INVALID = "configinstance.save.error6";
    public static final String MSG_CONFIGINSTANCE_CREATED_SUCCESSFULLY = "configinstance.save.success7";
    public static final String MSG_CONFIGINSTANCE_UPDATE_SUCCESS = "configinstance.save.success8";

    public static final String ERR_CONFIG_NOT_FOUND = "config.read.error3";
    public static final String ERR_CONFIG_READ_ERROR_USING_SHORTNAME = "config.read.error4";

    public static final String MSG_JOB_SAVE_SUCCESSFULLY = "clientjobconfig.save.success1";
    public static final String MSG_JOB_STATUS_CHANGED_SUCCESSFULLY = "clientjobconfig.save.success2";
    public static final String MSG_JOB_DELETED_SUCCESSFULLY = "clientjobconfig.save.success3";
    public static final String ERR_JOB_SAVE_FAILED = "clientjobconfig.save.error1";
    public static final String ERR_JOB_ALREADY_EXIST = "clientjobconfig.save.error2";
    public static final String ERR_MISSING_FIELD = "clientjobconfig.save.error3";
    public static final String ERR_JOB_DELETE_FAILED = "clientjobconfig.save.error4";
    public static final String ERR_JOB_STATUS_CHANGE_FAILED = "clientjobconfig.save.error5";
    public static final String ERR_UNABLE_TO_LOAD_JOBS = "clientjobconfig.read.error1";
    public static final String ERR_JOB_INVALID_ID = "clientjobconfig.read.error2";
    public static final String ERR_JOB_RUN_FAILED = "clientjobconfig.run.error1";
    public static final String MSG_JOB_RUN_SUCCESS = "clientjobconfig.run.success1";

}