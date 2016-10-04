package com.xo.web.mgr;

import com.xo.web.models.dao.ResourceTypeDaoImpl;
import com.xo.web.models.system.ResourceType;

public class ResourceTypeLogic extends BaseLogic<ResourceType, Integer> {

	public ResourceTypeLogic() {
		super(new ResourceTypeDaoImpl());
	}

}
