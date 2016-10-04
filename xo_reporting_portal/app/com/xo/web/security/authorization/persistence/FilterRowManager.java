package com.xo.web.security.authorization.persistence;

import com.xo.web.models.system.ResourceFilter;

public interface FilterRowManager {

	void applyFilter(ResourceFilter filter);

	void disableFilter(ResourceFilter filter);
}
