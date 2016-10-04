package com.xo.web.models.dao;

import com.xo.web.models.system.UserPermissionResourceInstance;
import com.xo.web.models.system.UserPermissionResourceInstanceId;

public interface UsersPermissionsResourceInstanceDAO extends 
						GenericDAO<UserPermissionResourceInstance, UserPermissionResourceInstanceId>, 
						ResourceInstanceExtractor,
						RowLevelPermissionsDAO<UserPermissionResourceInstance, UserPermissionResourceInstanceId>{

}
