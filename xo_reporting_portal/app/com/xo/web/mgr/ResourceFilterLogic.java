package com.xo.web.mgr;

import com.xo.web.core.XODAOException;
import com.xo.web.models.dao.ResourceFilterDAO;
import com.xo.web.models.dao.ResourceFilterDAOImpl;
import com.xo.web.models.system.PermissionEnum;
import com.xo.web.models.system.ResourceFilter;
import com.xo.web.models.system.ResourceFilterParameter;
import com.xo.web.models.system.ResourceType;
import com.xo.web.util.XoUtil;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

public class ResourceFilterLogic extends BaseLogic<ResourceFilter, Integer> {

	private static final int DEFAULT_INT_PARAM_VALUE = -99999;
	private static final String DEFAULT_STRING_PARAM_VALUE = "==XO==NO==ENTRIES==";
	private static final ResourceFilterDAO RESOURCE_FILTER_DAO = new ResourceFilterDAOImpl();
	
	public ResourceFilterLogic() {
		super(new ResourceFilterDAOImpl());
	}

	public Map<ResourceType, Set<ResourceFilter>> getResourceFilterMap(Set<PermissionEnum> permissionEnums) throws XODAOException {
		Map<ResourceType, Set<ResourceFilter>> resourceFiltersMap = new HashMap<ResourceType, Set<ResourceFilter>>();
		if(XoUtil.hasData(permissionEnums)) {
			Collection<ResourceFilter> resourceFilters = RESOURCE_FILTER_DAO.findByPermissionNames(permissionEnums);
			prepareResourceTypeFilterMap(resourceFiltersMap, resourceFilters);
		}
		return resourceFiltersMap;
	}

	public Map<ResourceType, Set<ResourceFilter>> getByResourceTypeId(Set<Integer> resourceTypeIds) throws XODAOException {
		Map<ResourceType, Set<ResourceFilter>> resourceFiltersMap = new HashMap<ResourceType, Set<ResourceFilter>>();
		if(XoUtil.hasData(resourceTypeIds)) {
			Collection<ResourceFilter> resourceFilters = RESOURCE_FILTER_DAO.findByResourceTypeIds(resourceTypeIds);
			prepareResourceTypeFilterMap(resourceFiltersMap, resourceFilters);
		}
		return resourceFiltersMap;
	}

	private void prepareResourceTypeFilterMap(Map<ResourceType, Set<ResourceFilter>> resourceFiltersMap, Collection<ResourceFilter> resourceFilters) {
		if(XoUtil.hasData(resourceFilters)) {
			for(ResourceFilter resourceFilter : resourceFilters) {
				ResourceType resourceType = resourceFilter.getResourceType();
				Set<ResourceFilter> tempResourceTypeFilters = resourceFiltersMap.get(resourceType);
				if(tempResourceTypeFilters == null) {
					tempResourceTypeFilters = new HashSet<ResourceFilter>();
					resourceFiltersMap.put(resourceType, tempResourceTypeFilters);
				}
				tempResourceTypeFilters.add(resourceFilter);
			}
		}
	}

	public void fillDefaultFilterParams(ResourceFilter resourceFilter) {
		if(resourceFilter != null) {
			Set<ResourceFilterParameter> filterParameters = resourceFilter.getResourceFilterParameters();
			if(XoUtil.hasData(filterParameters)) {
				for(ResourceFilterParameter filterParameter : filterParameters) {
					switch(filterParameter.getParamType()) {
						case STRING:
							filterParameter.setParameterValues(DEFAULT_STRING_PARAM_VALUE);
							break;
						case INT:
							filterParameter.setParameterValues(DEFAULT_INT_PARAM_VALUE);
							break;
						case LIST_INT:
							Set<Integer> intList = new HashSet<Integer>();
							intList.add(DEFAULT_INT_PARAM_VALUE);
							filterParameter.setParameterValues(intList);
							break;
						case LIST_STRING:
							Set<String> strList = new HashSet<String>();
							strList.add(DEFAULT_STRING_PARAM_VALUE);
							filterParameter.setParameterValues(strList);
							break;
					}
				}
			}
		}
	}
}
