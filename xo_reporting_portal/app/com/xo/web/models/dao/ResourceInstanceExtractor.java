package com.xo.web.models.dao;

import com.xo.web.core.XODAOException;
import com.xo.web.models.system.PermissionEnum;

import java.util.Collection;

public interface ResourceInstanceExtractor {

	Collection<Integer> countByUserIdAndPermissions(Integer userId, Collection<PermissionEnum> permissions) throws XODAOException;
	Collection<Object[]> findResourceInstanceIdsByUserIdAndPermission(Integer userId, Collection<PermissionEnum> permissions) throws XODAOException;

}