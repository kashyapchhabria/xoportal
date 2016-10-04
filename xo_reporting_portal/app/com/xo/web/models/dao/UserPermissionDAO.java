package com.xo.web.models.dao;

import java.util.Collection;

import com.xo.web.core.XODAOException;
import com.xo.web.models.system.UserPermission;

public interface UserPermissionDAO extends GenericDAO<UserPermission, Integer>{

	Collection<UserPermission> findAllByRlpIDAndResourceTypeId(Integer rlpTypeId, Integer resourceTypeId) throws XODAOException;
}
