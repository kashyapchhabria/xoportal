package com.xo.web.models.dao;

import java.util.Collection;

import org.hibernate.Query;

import com.xo.web.core.XODAOException;
import com.xo.web.models.system.PermissionEnum;
import com.xo.web.models.system.RolePermissionResourceInstance;
import com.xo.web.models.system.RolePermissionResourceInstanceId;

@SuppressWarnings("unchecked")
public class RolesPermissionsResourceInstanceDAOImpl
		extends RowLevelPermissionsDAOImpl<RolePermissionResourceInstance, RolePermissionResourceInstanceId>
		implements RolesPermissionsResourceInstanceDAO {

	private static final String QUERY_COUNT_RESOURCE_INSTANCES_BY_USER_ID_AND_PERMISSION_NAMES = "countResourceInstancesByUserIdAndPermissionNames";
	private static final String QUERY_FIND_RESOURCE_INSTANCE_IDS_BY_USER_ID_AND_PERMISSION_NAME = "findResourceInstanceIdsByUserIdAndPermissionName";
	private static final String PARAM_PERMISSION_NAMES = "permissionNames";
	private static final String PARAM_USER_ID = "userID";

	public Collection<Object[]> findResourceInstanceIdsByUserIdAndPermission(Integer userId, 
			Collection<PermissionEnum> permissions) throws XODAOException {
		Collection<Object[]> resourceAndInstanceIds = null;
		try{
			Query query = getNamedQuery(QUERY_FIND_RESOURCE_INSTANCE_IDS_BY_USER_ID_AND_PERMISSION_NAME);
			query.setParameter(PARAM_USER_ID, userId);
			query.setParameterList(PARAM_PERMISSION_NAMES, PermissionEnum.getAsString(permissions));
			resourceAndInstanceIds = query.list();
		} catch(Exception e) {
			throw new XODAOException(e);
		}
		return resourceAndInstanceIds;
	}

	@Override
	public Collection<Integer> countByUserIdAndPermissions(Integer userId, Collection<PermissionEnum> permissions) throws XODAOException {
		Collection<Integer> resourceAndInstanceIdsCount = null;
		try{
			Query query = getNamedQuery(QUERY_COUNT_RESOURCE_INSTANCES_BY_USER_ID_AND_PERMISSION_NAMES);
			query.setParameter(PARAM_USER_ID, userId);
			query.setParameterList(PARAM_PERMISSION_NAMES, PermissionEnum.getAsString(permissions));
			resourceAndInstanceIdsCount = (Collection<Integer>) query.list();
		} catch(Exception e) {
			throw new XODAOException(e);
		}
		return resourceAndInstanceIdsCount;
	}

}
