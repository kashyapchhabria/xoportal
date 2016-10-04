package com.xo.web.models.dao;

import com.xo.web.models.system.RolePermissionResourceInstance;
import com.xo.web.models.system.RolePermissionResourceInstanceId;

public interface RolesPermissionsResourceInstanceDAO extends 
				GenericDAO<RolePermissionResourceInstance, RolePermissionResourceInstanceId>, 
				ResourceInstanceExtractor, 
				RowLevelPermissionsDAO<RolePermissionResourceInstance, RolePermissionResourceInstanceId>{

}
