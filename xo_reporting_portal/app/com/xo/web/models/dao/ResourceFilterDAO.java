package com.xo.web.models.dao;


import com.xo.web.core.XODAOException;
import com.xo.web.models.system.PermissionEnum;
import com.xo.web.models.system.ResourceFilter;

import java.util.Collection;
import java.util.Set;

public interface ResourceFilterDAO extends GenericDAO<ResourceFilter, Integer> {

	Collection<ResourceFilter> findByPermissionNames(Collection<PermissionEnum> permissionEnums) throws XODAOException;
	Collection<ResourceFilter> findByResourceTypeIds(Set<Integer> resourceTypeIds) throws XODAOException;

}
