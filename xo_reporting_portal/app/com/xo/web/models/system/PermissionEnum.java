package com.xo.web.models.system;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.xo.web.util.XoUtil;

@SuppressWarnings("serial")
public enum PermissionEnum {

	READ_RESOURCE_TYPE,
	
	CREATE_USER,
	READ_USER,  
 	UPDATE_USER,
 	UPDATE_PASSWORD,
 	DEACTIVATE_USER,
 	ACTIVATE_USER,
 	DELETE_USER,

 	CREATE_USER_ROLE,
 	READ_USER_ROLE,
 	UPDATE_USER_ROLE,
 	DELETE_USER_ROLE,
 	ACTIVATE_USER_ROLE,
 	DEACTIVATE_USER_ROLE,

 	CREATE_USER_PERMISSION,
 	READ_USER_PERMISSION,
 	UPDATE_USER_PERMISSION,
 	ACTIVATE_USER_PERMISSION,
 	DEACTIVATE_USER_PERMISSION,
 	DELETE_USER_PERMISSION,

 	CREATE_ROLE,
 	READ_ROLE,
 	UPDATE_ROLE,
 	DEACTIVATE_ROLE,
 	ACTIVATE_ROLE,
 	DELETE_ROLE,

 	CREATE_ROLE_PERMISSION,
 	READ_ROLE_PERMISSION,
 	UPDATE_ROLE_PERMISSION,
 	DELETE_ROLE_PERMISSION,
 	ACTIVATE_ROLE_PERMISSION,
 	DEACTIVATE_ROLE_PERMISSION,

 	READ_PERMISSION,
 	DEACTIVATE_PERMISSION,
 	ACTIVATE_PERMISSION,

 	CREATE_SITE,
 	READ_SITE,
 	UPDATE_SITE,
 	DEACTIVATE_SITE,
 	ACTIVATE_SITE,
 	DELETE_SITE,

 	CREATE_TABLEAU_USER,
 	READ_TABLEAU_USER,
 	UPDATE_TABLEAU_USER,
 	DEACTIVATE_TABLEAU_USER,
 	ACTIVATE_TABLEAU_USER,
 	DELETE_TABLEAU_USER,

 	CREATE_TABLEAU_PROJECT,
 	READ_TABLEAU_PROJECT,
 	UPDATE_TABLEAU_PROJECT,
 	DEACTIVATE_TABLEAU_PROJECT,
 	ACTIVATE_TABLEAU_PROJECT,
 	DELETE_TABLEAU_PROJECT,

 	CREATE_TABLEAU_WORKBOOK,
 	READ_TABLEAU_WORKBOOK,
 	UPDATE_TABLEAU_WORKBOOK,
 	DEACTIVATE_TABLEAU_WORKBOOK,
 	ACTIVATE_TABLEAU_WORKBOOK,
 	DELETE_TABLEAU_WORKBOOK,

 	CREATE_TABLEAU_VIEW,
 	READ_TABLEAU_VIEW,
 	UPDATE_TABLEAU_VIEW,
 	DEACTIVATE_TABLEAU_VIEW,
 	ACTIVATE_TABLEAU_VIEW,
 	DELETE_TABLEAU_VIEW,

	// Tableau related permissions
 	SYNC_TABLEAU_DATA_VIA_REST,

 	CREATE_CONFIGURATION_TEMPLATE,
 	READ_CONFIGURATION_TEMPLATE,
 	UPDATE_CONFIGURATION_TEMPLATE,
 	DELETE_CONFIGURATION_TEMPLATE,
 	DEACTIVATE_CONFIGURATION_TEMPLATE,
 	ACTIVATE_CONFIGURATION_TEMPLATE,

 	CREATE_CONFIGURATION_INSTANCE,
 	READ_CONFIGURATION_INSTANCE,
 	UPDATE_CONFIGURATION_INSTANCE,
 	DELETE_CONFIGURATION_INSTANCE,
 	DEACTIVATE_CONFIGURATION_INSTANCE,
 	ACTIVATE_CONFIGURATION_INSTANCE,

 	CREATE_CLIENT,
 	READ_CLIENT,
 	UPDATE_CLIENT,
 	DELETE_CLIENT,
 	DEACTIVATE_CLIENT,
 	ACTIVATE_CLIENT,

 	CREATE_JOB,
 	READ_JOB,
 	UPDATE_JOB,
 	DELETE_JOB,
 	DEACTIVATE_JOB,
 	ACTIVATE_JOB,

 	CREATE_CLIENT_JOB_CONFIG,
 	READ_CLIENT_JOB_CONFIG,
 	UPDATE_CLIENT_JOB_CONFIG,
 	DELETE_CLIENT_JOB_CONFIG,
 	DEACTIVATE_CLIENT_JOB_CONFIG,
 	ACTIVATE_CLIENT_JOB_CONFIG,

 	CREATE_USER_PERMISSION_RESOURCE_INSTANCE,
 	READ_USER_PERMISSION_RESOURCE_INSTANCE,
 	UPDATE_USER_PERMISSION_RESOURCE_INSTANCE,
 	DELETE_USER_PERMISSION_RESOURCE_INSTANCE,
 	DEACTIVATE_USER_PERMISSION_RESOURCE_INSTANCE,
 	ACTIVATE_USER_PERMISSION_RESOURCE_INSTANCE,

 	CREATE_ROLE_PERMISSION_RESOURCE_INSTANCE,
 	READ_ROLE_PERMISSION_RESOURCE_INSTANCE,
 	UPDATE_ROLE_PERMISSION_RESOURCE_INSTANCE,
 	DELETE_ROLE_PERMISSION_RESOURCE_INSTANCE,
 	DEACTIVATE_ROLE_PERMISSION_RESOURCE_INSTANCE,
 	ACTIVATE_ROLE_PERMISSION_RESOURCE_INSTANCE,


	CREATE_VIEW_GROUP,
	READ_VIEW_GROUP,
	UPDATE_VIEW_GROUP,
	DELETE_VIEW_GROUP,
	DEACTIVATE_VIEW_GROUP,
	ACTIVATE_VIEW_GROUP,


	;

	public String getValue() {
		return this.name();
	};

	public static final List<String> getAsString(PermissionEnum...permissionEnums) {
		return getAsString(Arrays.asList(permissionEnums));
	}
	
	public static final List<String> getAsString(Collection<PermissionEnum> permissionEnums) {
		List<String> stringForm = new ArrayList<String>();
		if(XoUtil.hasData(permissionEnums)) {
			for(PermissionEnum permissionEnum : permissionEnums) {
				stringForm.add(permissionEnum.name());
			}
		}
		return stringForm;
	}

	public static final List<PermissionEnum> getUserMgmtPermissions() {
		return new ArrayList<PermissionEnum>() {{
			add(CREATE_USER);
			add(UPDATE_USER);
			add(DELETE_USER);
			add(ACTIVATE_USER);
			add(DEACTIVATE_USER);
		}};
	}

	public static final List<PermissionEnum> getUserRoleMgmtPermissions() {
		return new ArrayList<PermissionEnum>() {{
			add(CREATE_USER_ROLE);
			add(UPDATE_USER_ROLE);
			add(DELETE_USER_ROLE);
			add(ACTIVATE_USER_ROLE);
			add(DEACTIVATE_USER_ROLE);
		}};
	}

	public static final List<PermissionEnum> getUserPermissionMgmtPermissions() {
		return new ArrayList<PermissionEnum>() {{
			add(CREATE_USER_PERMISSION);
			add(UPDATE_USER_PERMISSION);
			add(ACTIVATE_USER_PERMISSION);
			add(DEACTIVATE_USER_PERMISSION);
			add(DELETE_USER_PERMISSION);
		}};
	}
	
	public static final List<PermissionEnum> getRoleMgmtPermissions() {
		return new ArrayList<PermissionEnum>() {{
			add(CREATE_ROLE);
			add(ACTIVATE_ROLE);
			add(DEACTIVATE_ROLE);
			add(DELETE_ROLE);
			add(UPDATE_ROLE);
		}};
	}

	public static final List<PermissionEnum> getRolePermissionMgmtPermissions() {
		return new ArrayList<PermissionEnum>() {{
			add(CREATE_ROLE_PERMISSION);
			add(UPDATE_ROLE_PERMISSION);
			add(ACTIVATE_ROLE_PERMISSION);
			add(DEACTIVATE_ROLE_PERMISSION);
			add(DELETE_ROLE_PERMISSION);
		}};
	}
	
	public static final List<PermissionEnum> getPermissionMgmtPermissions() {
		return new ArrayList<PermissionEnum>() {{
			add(READ_PERMISSION);
			add(ACTIVATE_PERMISSION);
			add(DEACTIVATE_PERMISSION);
		}};
	}

	public static final List<PermissionEnum> getTableauPermissions() {
		return new ArrayList<PermissionEnum>() {{
			add(READ_TABLEAU_PROJECT);
			add(READ_TABLEAU_WORKBOOK);
			add(READ_TABLEAU_VIEW);
		}};
	}


    public static final List<PermissionEnum> getConfigTemplatePermissions() {
        return new ArrayList<PermissionEnum>() {{
        	add(CREATE_CONFIGURATION_TEMPLATE);
            add(READ_CONFIGURATION_TEMPLATE);
            add(DELETE_CONFIGURATION_TEMPLATE);
            add(UPDATE_CONFIGURATION_TEMPLATE);
            add(DEACTIVATE_CONFIGURATION_TEMPLATE);
            add(ACTIVATE_CONFIGURATION_TEMPLATE);
        }};
    }

    public static final List<PermissionEnum> getConfigInstancePermissions() {
        return new ArrayList<PermissionEnum>() {{
            add(CREATE_CONFIGURATION_INSTANCE);
            add(READ_CONFIGURATION_INSTANCE);
            add(DELETE_CONFIGURATION_INSTANCE);
            add(UPDATE_CONFIGURATION_INSTANCE);
            add(DEACTIVATE_CONFIGURATION_INSTANCE);
            add(ACTIVATE_CONFIGURATION_INSTANCE);
        }};
    }
    
    public static final List<PermissionEnum> getUserRowLevePermissions() {
        return new ArrayList<PermissionEnum>() {{
        	add(CREATE_USER_PERMISSION_RESOURCE_INSTANCE);
            add(READ_USER_PERMISSION_RESOURCE_INSTANCE);
            add(UPDATE_USER_PERMISSION_RESOURCE_INSTANCE);
            add(DELETE_USER_PERMISSION_RESOURCE_INSTANCE);
            add(DEACTIVATE_USER_PERMISSION_RESOURCE_INSTANCE);
            add(ACTIVATE_USER_PERMISSION_RESOURCE_INSTANCE);
        }};
    }

    public static final List<PermissionEnum> getRoleRowLevePermissions() {
        return new ArrayList<PermissionEnum>() {{
        	add(CREATE_ROLE_PERMISSION_RESOURCE_INSTANCE);
            add(READ_ROLE_PERMISSION_RESOURCE_INSTANCE);
            add(UPDATE_ROLE_PERMISSION_RESOURCE_INSTANCE);
            add(DELETE_ROLE_PERMISSION_RESOURCE_INSTANCE);
            add(DEACTIVATE_ROLE_PERMISSION_RESOURCE_INSTANCE);
            add(ACTIVATE_ROLE_PERMISSION_RESOURCE_INSTANCE);
        }};
    }

    public static final List<PermissionEnum> getClientPermissions() {
        return new ArrayList<PermissionEnum>() {{
        	add(CREATE_CLIENT);
        	add(READ_CLIENT);
        	add(UPDATE_CLIENT);
        	add(DELETE_CLIENT);
        	add(DEACTIVATE_CLIENT);
        	add(ACTIVATE_CLIENT);
        }};
    }

	 public static final List<PermissionEnum> getJobPermissions() {
	        return new ArrayList<PermissionEnum>() {{
	        	add(CREATE_JOB);
	        	add(READ_JOB);
	        	add(UPDATE_JOB);
	        	add(DELETE_JOB);
	        	add(DEACTIVATE_JOB);
	        	add(ACTIVATE_JOB);
	        }};
	 }

	 public static final List<PermissionEnum> getClientJobConfigPermissions() {
	        return new ArrayList<PermissionEnum>() {{
	        	add(CREATE_CLIENT_JOB_CONFIG);
	        	add(READ_CLIENT_JOB_CONFIG);
	        	add(UPDATE_CLIENT_JOB_CONFIG);
	        	add(DELETE_CLIENT_JOB_CONFIG);
	        	add(DEACTIVATE_CLIENT_JOB_CONFIG);
	        	add(ACTIVATE_CLIENT_JOB_CONFIG);
	        }};
	 }
}
