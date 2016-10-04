package com.xo.web.models.dao;


import java.util.Collection;

import org.hibernate.Query;

import com.xo.web.core.XODAOException;
import com.xo.web.models.system.RolePermission;

@SuppressWarnings("unchecked")
public class RolePermissionDAOImpl extends GenericDAOImpl<RolePermission, Integer> implements RolePermissionDAO {

	private static final String QUERY_FIND_ALL_BY_RLP_TYPE_ID_AND_RESOURCE_TYPE_ID = "findAllByRlpIDAndResourceTypeId";
	private static final String PARAM_RLP_TYPE_ID = "rlpTypeId";
	private static final String PARAM_RESOURCE_TYPE_ID = "resourceTypeId";

	@Override
	public Collection<RolePermission> findAllByRlpIDAndResourceTypeId(Integer rlpTypeId, Integer resourceTypeId) throws XODAOException {
		Collection<RolePermission> rolePermissions = null;
		try{
			Query query = getNamedQuery(QUERY_FIND_ALL_BY_RLP_TYPE_ID_AND_RESOURCE_TYPE_ID);
			query.setParameter(PARAM_RLP_TYPE_ID, rlpTypeId);
			query.setParameter(PARAM_RESOURCE_TYPE_ID, resourceTypeId);
			rolePermissions = query.list();
		} catch(Exception e) {
			throw new XODAOException(e);
		}
		return rolePermissions;
	}

}
