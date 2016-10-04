package com.xo.web.models.dao;

import com.xo.web.core.XODAOException;
import com.xo.web.models.system.Role;

import java.util.Collection;
import java.util.List;

public interface RoleDAO extends GenericDAO<Role, Integer>{

	Role findByName(String name) throws XODAOException;
	Collection<Role> findByActive(boolean activeState) throws XODAOException;
	List<Role> findUnassignedRoles(Integer userId) throws XODAOException;
}
