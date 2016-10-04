package com.xo.web.mgr;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.xo.web.core.XODAOException;
import com.xo.web.models.dao.GenericDAO;
import com.xo.web.models.dao.ResourceTypeDao;
import com.xo.web.models.dao.ResourceTypeDaoImpl;
import com.xo.web.models.dao.RowLevelPermissionsDAO;
import com.xo.web.models.system.IEntity;
import com.xo.web.models.system.ResourceType;
import com.xo.web.util.XoUtil;
import com.xo.web.viewdtos.KeyValueDTO;
import com.xo.web.viewdtos.RowLevelPermissionDto;

public abstract class RowLevelPermissionLogic<T extends IEntity, ID extends Serializable> extends BaseLogic<T, ID> {

	private final RowLevelPermissionsDAO<T, ID> rowLevelPermissionsDAO;
	protected final ResourceTypeDao resourceTypeDao;

	public RowLevelPermissionLogic(GenericDAO<T, ID> entityDao) {
		super(entityDao);
		this.rowLevelPermissionsDAO = (RowLevelPermissionsDAO<T, ID>) this.entityDao;
		this.resourceTypeDao = new ResourceTypeDaoImpl();
	}

	public Set<RowLevelPermissionDto> findAllRecords() {
		Collection<T> rowLevelPermissionResourceInstances = this.entityDao.findAll();
		return this.convertEntitiesToDtos(rowLevelPermissionResourceInstances);
	}

	public  Set<RowLevelPermissionDto> findAllByRlpTypeIdAndResourceType(Integer rlpTypeId, Integer resourceTypeId) throws XODAOException {
		Collection<T> rlpRecords = this.rowLevelPermissionsDAO.findAllByRlpTypeIdAndResourceType(rlpTypeId, resourceTypeId);
		return this.convertEntitiesToDtos(rlpRecords);
	}

	public Collection<String> findAllResourceInstanceIdsByRlpTypeIdAndResourceType(Integer rlpTypeId, Integer resourceTypeId) throws XODAOException {
		Collection<String> resourceInstanceIds = new HashSet<String>();
		Collection<String> rawResourceInstanceIds = this.rowLevelPermissionsDAO.findResourceInstanceIdsByRlpIDAndResourceTypeId(rlpTypeId, resourceTypeId);
		if(XoUtil.hasData(rawResourceInstanceIds)) {
			for(String record : rawResourceInstanceIds) {
				resourceInstanceIds.add(record.toString());
			}
		}
		return resourceInstanceIds;
	}

	public Collection<KeyValueDTO> findAllEntityInstances(Integer rlpTypeId, Integer resourceTypeId) throws XODAOException {
		Collection<KeyValueDTO> entityInstanceDtos = new HashSet<KeyValueDTO>();
		if(XoUtil.isNotNull(rlpTypeId, resourceTypeId)) {

			Collection<String> excludedInstanceIds = this.findAllResourceInstanceIdsByRlpTypeIdAndResourceType(rlpTypeId, resourceTypeId);
			ResourceType resourceType = this.resourceTypeDao.find(resourceTypeId);

			if(resourceType != null) {
				Collection<? extends IEntity> entityInstances = 
						this.rowLevelPermissionsDAO.findAllInstances(resourceType.getFullyQualifiedName(), excludedInstanceIds);
				if(XoUtil.hasData(entityInstances)) {
					for(IEntity entity : entityInstances) {
						entityInstanceDtos.add(entity.asKeyValue());
					}
				}
			}
		}
		return entityInstanceDtos;
	}

	protected abstract Set<RowLevelPermissionDto> convertEntitiesToDtos(Collection<T> rolePermissionResourceInstances);
}
