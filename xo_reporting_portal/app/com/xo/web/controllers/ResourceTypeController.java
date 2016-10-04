package com.xo.web.controllers;

import play.mvc.Result;

import com.xo.web.mgr.ResourceTypeLogic;
import com.xo.web.models.system.PermissionEnum;
import com.xo.web.models.system.ResourceType;
import com.xo.web.security.authorization.action.Authroize;

public class ResourceTypeController extends
		BaseController<ResourceType, Integer> {

	public ResourceTypeController() {
		super(new ResourceTypeLogic());
	}

	@Authroize(permissions = {PermissionEnum.READ_RESOURCE_TYPE})
	public Result readAllAsKeyValue() {
		return super.readAllAsKeyValue();
	}
}
