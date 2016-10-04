package com.xo.web.models.dao;


import com.xo.web.core.XODAOException;
import com.xo.web.models.system.Role;
import org.hibernate.Query;

import java.util.Collection;
import java.util.List;


@SuppressWarnings("unchecked")
public class RoleDAOImpl extends GenericDAOImpl<Role, Integer> implements RoleDAO{

	private static final String QUERY_SELECT_BY_NAME = "findByName";
	private static final String QUERY_SELECT_BY_ACTIVE = "findByActive";
	private static final String QUERY_SELECT_BY_UNASSIGNED = "findUnassignedRoles";
	private static final String PARAM_ROLE_NAME = "name";
	private static final String PARAM_ACTIVE_STATE ="activestate";
	private static final String PARAM_USERID="userId";

	public Role findByName(String name)  throws XODAOException{
		Query query = getNamedQuery(QUERY_SELECT_BY_NAME);
		query.setParameter(PARAM_ROLE_NAME, name);
		return (Role) query.uniqueResult();
	}

	public Collection<Role> findByActive(boolean activeState)  throws XODAOException{
		Query query = getNamedQuery(QUERY_SELECT_BY_ACTIVE);
		query.setParameter(PARAM_ACTIVE_STATE, activeState);
		return (Collection<Role>) query.list();
	}

	public List<Role> findUnassignedRoles(Integer userId)  throws XODAOException{
		Query query = getNamedQuery(QUERY_SELECT_BY_UNASSIGNED);
		query.setParameter(PARAM_USERID, userId);
		return (List<Role>) query.list();
	}
}
