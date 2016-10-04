package com.xo.web.security.authorization.persistence;

import com.xo.web.core.XODAOException;
import com.xo.web.mgr.TokenActionLogic;
import com.xo.web.models.dao.*;
import com.xo.web.models.system.PermissionEnum;
import com.xo.web.models.system.User;
import com.xo.web.util.XoUtil;
import play.libs.F.Promise;
import play.mvc.Action;
import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Results;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Security related aspects.
 * @author sekar
 *
 */
public class SecureRowLevelHandler {

	private final TokenActionLogic TOKEN_ACTION_LOGIC = new TokenActionLogic();
	private final Set<PermissionEnum> permissionEnums;
	private final Context currentContext;

	public SecureRowLevelHandler(final Context ctx, final PermissionEnum[] permissionEnums) {
		this.permissionEnums = XoUtil.hasData(permissionEnums) ? new HashSet<PermissionEnum>(Arrays.asList(permissionEnums)) : null;
		this.currentContext = ctx;
	}

	/**
	 * Securing the resources based on the requested permissions.
	 * @param requestHandler
	 * @return
	 * @throws Throwable
	 */
	public Promise<Result> enableAndInjectResourceFilters(Action<?> requestHandler) throws Throwable {

		Promise<Result> resultObject = null;
		if(requestHandler != null && XoUtil.hasData(this.permissionEnums)) {

			final RolesPermissionsResourceInstanceDAO ROLE_RESOURCE_INSTANCE_DAO = new RolesPermissionsResourceInstanceDAOImpl();
			final UsersPermissionsResourceInstanceDAO USER_RESOURCE_INSTANCE_DAO = new UsersPermissionsResourceInstanceDAOImpl();
			
			Integer userId = getUserId();
			if(userId > 0) {
				Set<Integer> resourceTypeIds = new HashSet<Integer>();
				Collection<Integer> resourceAndInstanceIdsCount = isUserHaveResourceInstances(ROLE_RESOURCE_INSTANCE_DAO, userId, this.permissionEnums);
				resourceTypeIds = mergeCollections(resourceAndInstanceIdsCount, resourceTypeIds);
				
				resourceAndInstanceIdsCount = isUserHaveResourceInstances(USER_RESOURCE_INSTANCE_DAO, userId, this.permissionEnums);
				resourceTypeIds = mergeCollections(resourceAndInstanceIdsCount, resourceTypeIds);

				if(resourceTypeIds.size() > 0) {	// Resource instances are available for the some resoure types 

					// Enable filters based on instance restrictions.
					RowLevelFilter rowLevelFilter = new RowLevelFilter(userId, this.permissionEnums);
					rowLevelFilter.setAvailableResourceIds(resourceTypeIds);
					rowLevelFilter.setFilterRowManager(new HibernateFilteringSupportFilterRowManager());
					rowLevelFilter.enableResourceFilters();
					resultObject = requestHandler.call(this.currentContext);
					rowLevelFilter.disableResourceFilters();
				} else {
					resultObject = requestHandler.call(this.currentContext);
				}
			} else {
				resultObject=redirectToLogin();
			}
		} else if(requestHandler != null) {
			resultObject = requestHandler.call(this.currentContext);
		} else {
			resultObject = redirectToLogin();
		}
		return resultObject;
	}

	private Promise<Result> redirectToLogin() {
		return Promise.pure((Result)Results.redirect(com.xo.web.controllers.routes.UserController.renderLoginPage()));
	}

	/**
	 * Checking whether the user have any row level restrictions.
	 * @param resourceInstanceExtractor
	 * @param userId
	 * @param permissions
	 * @return
	 * @throws XODAOException
	 */
	private Collection<Integer> isUserHaveResourceInstances(ResourceInstanceExtractor resourceInstanceExtractor,Integer userId, 
			Set<PermissionEnum> permissions) throws XODAOException {
		Collection<Integer> resourceAndInstanceIdsCount = null;
		if(XoUtil.hasData(permissions)) {
			resourceAndInstanceIdsCount = resourceInstanceExtractor.countByUserIdAndPermissions(userId, permissions);
		}
		return resourceAndInstanceIdsCount;
	}

	private Set<Integer> mergeCollections(final Collection<Integer> source, final Set<Integer> destination) {
		if(XoUtil.hasData(source)) {
			for(Integer tempRecord : source) {
				destination.add(tempRecord);
			}
		}
		return destination;
	}

	private int getUserId() {
		String authToken = Context.current().request().getHeader(XoUtil.HEADER_AUTH_TOKEN);
		if(!XoUtil.isNotNull(authToken)) {	// User logged in first time so header entries won't be available.			
			authToken = Context.current().session().get(XoUtil.HEADER_AUTH_TOKEN);
		}
		if(!XoUtil.isNotNull(authToken)) {	// Get it from query params.
			authToken = Context.current().request().getQueryString(XoUtil.AUTH_TOKEN_QUERY_PARAM);
		}
		User user = TOKEN_ACTION_LOGIC.getRestConnectedUser(authToken);
		return user != null ? user.getUserId() : -1;
	}
}
