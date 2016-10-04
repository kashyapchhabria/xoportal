package com.xo.web.models.dao;

import java.io.Serializable;
import java.util.Collection;

import com.xo.web.core.XODAOException;
import com.xo.web.models.system.IEntity;

public interface RowLevelPermissionsDAO<T, ID extends Serializable> extends GenericDAO<T, ID> {

	Collection<T> findAllByRlpTypeIdAndResourceType(Integer rlpTypeId, Integer resourceTypeId) throws XODAOException;
	Collection<? extends IEntity> findAllInstances(String resourceType, Collection<String> unwantedResourceInstanceIds) throws XODAOException;
	Collection<String> findResourceInstanceIdsByRlpIDAndResourceTypeId(Integer rlpTypeId, Integer resourceTypeId) throws XODAOException;
}
