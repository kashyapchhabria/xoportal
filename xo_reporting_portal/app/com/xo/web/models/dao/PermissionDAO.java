package com.xo.web.models.dao;

import com.xo.web.core.XODAOException;
import com.xo.web.models.system.Permission;

import java.util.List;

public interface PermissionDAO extends GenericDAO<Permission, Integer>{
    List<Permission> findAllUnAvailablePermission(Integer roleid) throws XODAOException;
	List<Permission> findUnassignedPermissions(Integer userId) throws XODAOException;

}
