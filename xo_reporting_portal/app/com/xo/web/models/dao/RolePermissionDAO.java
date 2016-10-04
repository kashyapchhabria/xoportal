package com.xo.web.models.dao;

import java.util.Collection;

import com.xo.web.core.XODAOException;
import com.xo.web.models.system.RolePermission;

public interface RolePermissionDAO extends GenericDAO<RolePermission, Integer>{

	Collection<RolePermission> findAllByRlpIDAndResourceTypeId(Integer rlpTypeId, Integer resourceTypeId) throws XODAOException;
	
}
