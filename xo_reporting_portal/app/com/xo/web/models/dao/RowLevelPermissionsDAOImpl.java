package com.xo.web.models.dao;

import java.io.Serializable;
import java.util.Collection;

import org.hibernate.Query;
import org.hibernate.Session;

import com.xo.web.core.XODAOException;
import com.xo.web.models.system.IEntity;
import com.xo.web.util.XoUtil;

@SuppressWarnings("unchecked")
public class RowLevelPermissionsDAOImpl<T, ID extends Serializable> extends GenericDAOImpl<T, ID> implements RowLevelPermissionsDAO<T, ID> {

	private static final String NOT_IN_INSTANCE_IDS = " NOT IN (:instanceIds) ";
	private static final String WHERE_ENTITY_ALIAS = " WHERE entityAlias.";
	private static final String ENTITY_ALIAS = " entityAlias ";
	private static final String SELECT_ENTITY_ALIAS_FROM = "select entityAlias from ";
	private static final String QUERY_FIND_BY_RLP_TYPE_ID_AND_RESOURCE_TYPE_ID = "findAllByRlpIDAndResourceTypeId";
	private static final String QUERY_FIND_RESOURCE_INSTANCE_IDS_BY_RLP_TYPE_ID_AND_RESOURCE_TYPE_ID = "findResourceInstanceIdsByRlpIDAndResourceTypeId";
	private static final String PARAM_RLP_TYPE_ID = "rlpTypeId";
	private static final String PARAM_RESOURCE_TYPE_ID = "resourceTypeId";
	private static final String PARAM_INSTANCEIDS = "instanceIds";

	public Collection<T> findAllByRlpTypeIdAndResourceType(Integer rlpTypeId, Integer resourceTypeId) throws XODAOException {
		Collection<T> rlpResourceInstances = null;
		try{
			Query query = getNamedQuery(QUERY_FIND_BY_RLP_TYPE_ID_AND_RESOURCE_TYPE_ID);
			query.setParameter(PARAM_RLP_TYPE_ID, rlpTypeId);
			query.setParameter(PARAM_RESOURCE_TYPE_ID, resourceTypeId);
			rlpResourceInstances = (Collection<T>) query.list();
		} catch(Exception e) {
			throw new XODAOException(e);
		}
		return rlpResourceInstances;
	}

	public Collection<? extends IEntity> findAllInstances(String resourceType, Collection<String> excludedInstanceIds) throws XODAOException {
		Collection<? extends IEntity> resourceInstances = null;
		Session session = ((Session)this.em().getDelegate());
		StringBuilder queryString = new StringBuilder(SELECT_ENTITY_ALIAS_FROM);
		queryString.append(resourceType);
		queryString.append(ENTITY_ALIAS);
		boolean isExclusionAvailable = XoUtil.hasData(excludedInstanceIds);
		if(isExclusionAvailable) {
			queryString.append(WHERE_ENTITY_ALIAS);
			try {
				queryString.append(this.getMetadataUtil().get(resourceType).getIdProperty());
			} catch (IllegalArgumentException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			queryString.append(NOT_IN_INSTANCE_IDS);
		}
		Query query = session.createQuery(queryString.toString());
		if(isExclusionAvailable) {
			query.setParameterList(PARAM_INSTANCEIDS, excludedInstanceIds);
		}
		resourceInstances = query.list();
		return resourceInstances;
	}

	@Override
	public Collection<String> findResourceInstanceIdsByRlpIDAndResourceTypeId(Integer rlpTypeId, Integer resourceTypeId) throws XODAOException {
		Collection<String> rlpResourceInstances = null;
		try{
			Query query = this.getNamedQuery(QUERY_FIND_RESOURCE_INSTANCE_IDS_BY_RLP_TYPE_ID_AND_RESOURCE_TYPE_ID);
			query.setParameter(PARAM_RLP_TYPE_ID, rlpTypeId);
			query.setParameter(PARAM_RESOURCE_TYPE_ID, resourceTypeId);
			rlpResourceInstances = query.list();
		} catch(Exception e) {
			throw new XODAOException(e);
		}
		return rlpResourceInstances;
	}

}
