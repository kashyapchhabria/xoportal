package com.xo.web.models.dao;

import java.io.Serializable;
import java.util.ArrayList;
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
			try {
				query.setParameterList(PARAM_INSTANCEIDS, this.getValidInstanceIds(excludedInstanceIds, this.getMetadataUtil().get(resourceType).getIdType().getJavaClass()));
			} catch (IllegalArgumentException | ClassNotFoundException e) {
				throw new XODAOException("Error setting the exclusion list parameter.", e);
			}
		}
		resourceInstances = query.list();
		return resourceInstances;
	}

	private Collection<?> getValidInstanceIds(Collection<String> excludedInstanceIds, Class<?> expectedType) {
		if(Integer.class == expectedType) {
			return toInteger(excludedInstanceIds);
		} else if(Long.class == expectedType) {
			return toLong(excludedInstanceIds);
		} else if(String.class == expectedType) {
			return excludedInstanceIds;
		}
		return null;
	}

	private final Collection<Integer> toInteger(Collection<String> excludedInstanceIds) {
		Collection<Integer> validIds = new ArrayList<Integer>();
		for(String strId : excludedInstanceIds) {
			validIds.add(Integer.parseInt(strId));
		}
		return validIds;
	}

	private final Collection<Long> toLong(Collection<String> excludedInstanceIds) {
		Collection<Long> validIds = new ArrayList<Long>();
		for(String strId : excludedInstanceIds) {
			validIds.add(Long.parseLong(strId));
		}
		return validIds;
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
