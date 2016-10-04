package com.xo.web.models.dao;

import com.xo.web.core.XODAOException;
import com.xo.web.models.system.Permission;
import org.hibernate.Query;

import java.util.List;

@SuppressWarnings("unchecked")
public class PermissionDAOImpl extends GenericDAOImpl<Permission, Integer> implements PermissionDAO{

    private static final String QUERY_SELECT_BY_ACTIVE = "findAllUnAvailablePermission";
    private static final String PARAM_ROLE_ID = "roleid";
    private static final String QUERY_SELECT_BY_UNASSIGNED = "findUnassignedPermissions";
    private static final String PARAM_USERID="userId";

    public List<Permission> findAllUnAvailablePermission(Integer roleid)  throws XODAOException{
        Query query = getNamedQuery(QUERY_SELECT_BY_ACTIVE);
        query.setParameter(PARAM_ROLE_ID, roleid);
        return (List<Permission>) query.list();
    }

	public List<Permission> findUnassignedPermissions(Integer userId)  throws XODAOException{
		Query query = getNamedQuery(QUERY_SELECT_BY_UNASSIGNED);
		query.setParameter(PARAM_USERID, userId);
		return (List<Permission>) query.list();
	}
}
