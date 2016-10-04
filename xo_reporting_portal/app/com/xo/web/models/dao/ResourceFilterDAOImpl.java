package com.xo.web.models.dao;

import com.xo.web.core.XODAOException;
import com.xo.web.models.system.PermissionEnum;
import com.xo.web.models.system.ResourceFilter;
import org.hibernate.Query;

import java.util.Collection;
import java.util.Set;

@SuppressWarnings("unchecked")
public class ResourceFilterDAOImpl extends GenericDAOImpl<ResourceFilter, Integer> implements ResourceFilterDAO {

	private static final String QUERY_FIND_BY_PERMISSIONS = "findByPermissions";
	private static final String PARAM_PERMISSION_NAMES = "permissionNames";
	private static final String QUERY_FIND_BY_RESOURCE_TYPE_IDS = "findByResourceTypeIds";
	private static final String PARAM_RESOURCE_TYPE_IDS = "resourceTypeIds";

	@Override
	public Collection<ResourceFilter> findByPermissionNames(Collection<PermissionEnum> permissionEnums) throws XODAOException {

		Query query = getNamedQuery(QUERY_FIND_BY_PERMISSIONS);
		query.setParameterList(PARAM_PERMISSION_NAMES, PermissionEnum.getAsString(permissionEnums));
		return query.list();
	}

	@Override
	public Collection<ResourceFilter> findByResourceTypeIds(Set<Integer> resourceTypeIds) throws XODAOException {

		Query query = getNamedQuery(QUERY_FIND_BY_RESOURCE_TYPE_IDS);
		query.setParameterList(PARAM_RESOURCE_TYPE_IDS, resourceTypeIds);
		return query.list();
	}
}
