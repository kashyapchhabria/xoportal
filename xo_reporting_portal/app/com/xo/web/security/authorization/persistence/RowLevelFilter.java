package com.xo.web.security.authorization.persistence;

import com.xo.web.core.XODAOException;
import com.xo.web.mgr.ResourceFilterLogic;
import com.xo.web.models.dao.RolesPermissionsResourceInstanceDAO;
import com.xo.web.models.dao.RolesPermissionsResourceInstanceDAOImpl;
import com.xo.web.models.dao.UsersPermissionsResourceInstanceDAO;
import com.xo.web.models.dao.UsersPermissionsResourceInstanceDAOImpl;
import com.xo.web.models.dao.ResourceInstanceExtractor;
import com.xo.web.models.system.PermissionEnum;
import com.xo.web.models.system.ResourceFilter;
import com.xo.web.models.system.ResourceFilterParameter;
import com.xo.web.models.system.ResourceType;
import com.xo.web.util.XoUtil;
import play.Logger;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Collection;


public class RowLevelFilter {

	private final RolesPermissionsResourceInstanceDAO ROLE_RESOURCE_INSTANCE_DAO = new RolesPermissionsResourceInstanceDAOImpl();
	private final UsersPermissionsResourceInstanceDAO USER_RESOURCE_INSTANCE_DAO = new UsersPermissionsResourceInstanceDAOImpl();
	private final ResourceFilterLogic RESOURCE_FILTER_MGR = new ResourceFilterLogic();

	private Set<Integer> availableResourceIds;
	private Map<ResourceType, Set<ResourceFilter>> resourceToFilterMap;
	private Map<Integer, Set<Object>> resourceInstanceIds = new HashMap<Integer, Set<Object>>();
	private FilterRowManager filterRowManager;
	private Integer userId;
	private Set<PermissionEnum> permissionEnums;
	
	public RowLevelFilter(Integer userId, Set<PermissionEnum> permissionEnums) {
		this.userId = userId;
		this.permissionEnums = permissionEnums;
	}

	public void enableResourceFilters() throws XODAOException {

		if(XoUtil.hasData(availableResourceIds)) {
			this.resourceToFilterMap = RESOURCE_FILTER_MGR.getByResourceTypeId(availableResourceIds);
			populateResourceInstanceIds(ROLE_RESOURCE_INSTANCE_DAO, userId, this.permissionEnums);
			populateResourceInstanceIds(USER_RESOURCE_INSTANCE_DAO, userId, this.permissionEnums);
			fillFilterParameters();

			/* Iterate through the list of resources filters and apply the filters and proper filter parameters. */
			Set<Entry<ResourceType, Set<ResourceFilter>>> resourcesFilters = resourceToFilterMap.entrySet();
			for (Entry<ResourceType, Set<ResourceFilter>> resourceFilter: resourcesFilters) {
				Set<ResourceFilter> filters = resourceFilter.getValue();
				if(XoUtil.hasData(filters)) {				
					for(ResourceFilter filter : filters) {
						filterRowManager.applyFilter(filter);
						Logger.info("Enabled filter : " + filter.getFullyQualifiedName());
					}
				}
			}
		}
	}

	private void fillFilterParameters() {
		if(XoUtil.hasData(resourceToFilterMap)) {
			if(XoUtil.hasData(resourceInstanceIds)) {
				Set<Entry<ResourceType, Set<ResourceFilter>>> resourcesFilters = resourceToFilterMap.entrySet();
				for (Entry<ResourceType, Set<ResourceFilter>> resourceFilter: resourcesFilters) {
					ResourceType resource = resourceFilter.getKey();
					Set<ResourceFilter> filters = resourceFilter.getValue();
					Set<Object> paramValues = resourceInstanceIds.get(resource.getResourceTypeId());
					if(XoUtil.hasData(filters)) {
						for(ResourceFilter filter : filters) {
							Set<ResourceFilterParameter> resourceFilterParamerters = filter.getResourceFilterParameters();
							if(XoUtil.hasData(resourceFilterParamerters) && XoUtil.hasData(paramValues)) {
								for(ResourceFilterParameter resourceFilterParamerter : resourceFilterParamerters) {
									resourceFilterParamerter.setParameterValues(paramValues);
								}
							} else {
								RESOURCE_FILTER_MGR.fillDefaultFilterParams(filter);
							}
						}
					}
				}
			} else {	// If no instance ids are available then we need to set the default filter parameters.
				Set<Entry<ResourceType, Set<ResourceFilter>>> resourcesFilters = resourceToFilterMap.entrySet();
				for (Entry<ResourceType, Set<ResourceFilter>> resourceFilter: resourcesFilters) {
					Set<ResourceFilter> filters = resourceFilter.getValue();
					if(XoUtil.hasData(filters)) {
						for(ResourceFilter filter : filters) {
							RESOURCE_FILTER_MGR.fillDefaultFilterParams(filter);
						}
					}
				}
			}
		}
	}

	public void disableResourceFilters() {

		/* Iterate through the list of resources verify that the resource is active
		 * If the resource is active apply the Filter to the ORM session. */
		/* Iterate through the list of resources filters and apply the filters and proper filter parameters. */
		if(XoUtil.hasData(availableResourceIds)) {
			Set<Entry<ResourceType, Set<ResourceFilter>>> resourcesFilters = resourceToFilterMap.entrySet();
			for (Entry<ResourceType, Set<ResourceFilter>> resourceFilter: resourcesFilters) {
				Set<ResourceFilter> filters = resourceFilter.getValue();
				if(XoUtil.hasData(filters)) {
					for(ResourceFilter filter : filters) {
						filterRowManager.disableFilter(filter);
						Logger.info("Disabled filter : " + filter.getFullyQualifiedName());
					}
				}
			}
		}
	}

	public void populateResourceInstanceIds(ResourceInstanceExtractor resourceInstanceExtractor,Integer userId,
			Set<PermissionEnum> permissions) throws XODAOException {
		if(XoUtil.hasData(permissions)) {
			Collection<Object[]> tempResourceInstanceIds = resourceInstanceExtractor.findResourceInstanceIdsByUserIdAndPermission(userId, permissions);
			if(XoUtil.hasData(tempResourceInstanceIds)) {
				for(Object[] tempRecord : tempResourceInstanceIds) {
					Integer resourceTypeId = (Integer) tempRecord[0];
					Object resourceInstanceId = tempRecord[1];
					Set<Object> instanceIds = resourceInstanceIds.get(resourceTypeId);
					if(instanceIds == null) {
						instanceIds = new HashSet<Object>();
						resourceInstanceIds.put(resourceTypeId, instanceIds);
					}
					instanceIds.add(resourceInstanceId);
				}
			}
		}
	}

	public Map<ResourceType, Set<ResourceFilter>> getResourceToFilterMap() {
		return resourceToFilterMap;
	}

	public void setResourceToFilterMap(Map<ResourceType, Set<ResourceFilter>> resourceToFilterMap) {
		this.resourceToFilterMap = resourceToFilterMap;
	}

	public void setFilterRowManager(FilterRowManager filterRowManager) {
		this.filterRowManager = filterRowManager;
	}

	public Set<Integer> getAvailableResourceIds() {
		return availableResourceIds;
	}

	public void setAvailableResourceIds(Set<Integer> availableResourceIds) {
		this.availableResourceIds = availableResourceIds;
	}

}
