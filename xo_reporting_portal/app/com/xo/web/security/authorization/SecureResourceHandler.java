package com.xo.web.security.authorization;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.xo.web.mgr.TokenActionLogic;
import com.xo.web.mgr.UserLogic;
import com.xo.web.models.system.PermissionEnum;
import com.xo.web.models.system.User;
import com.xo.web.util.XoUtil;

import play.mvc.Http.Context;

public class SecureResourceHandler {

	private final UserLogic USER_LOGIC = new UserLogic();
	private final TokenActionLogic TOKEN_ACTION_LOGIC = new TokenActionLogic();

	public SecureResourceHandler() {
	}

	public boolean isAllowed(final String name, final String meta, final Context context) {

		String authToken = this.USER_LOGIC.getCurrentAuthToken();
		if(XoUtil.isNotNull(authToken)) {
			if(USER_LOGIC.verifyAuthToken(authToken)) {
				User user = TOKEN_ACTION_LOGIC.getRestConnectedUser(authToken);
				if(user != null && user.isActive()) {

					// Update user last apperence time.
					Date curDate = new Date();
					user.setLastModifiedDate(curDate);
					user.setLastLoginDt(curDate);

					TOKEN_ACTION_LOGIC.updateRestTokenPingTime(authToken);	// Update the last pinged time.
					if(user.isSuperUser()) {					// All resources are allowed for super users.
						return true;
					} else {
						Set<PermissionEnum> permissions = user.getPermissionEnums();
						return XoUtil.hasData(permissions) && permissions.contains(PermissionEnum.valueOf(name));
					}
				}
			}
		}
		return false;
	}

	public final boolean isAllowed(final Context context, final PermissionEnum...permissions) {

		String authToken = this.USER_LOGIC.getCurrentAuthToken();
		if(XoUtil.isNotNull(authToken)) {
			if(USER_LOGIC.verifyAuthToken(authToken)) {
				User user = TOKEN_ACTION_LOGIC.getRestConnectedUser(authToken);

				if(user != null && user.isActive()) {

					// Update user last apperence time.
					Date curDate = new Date();
					user.setLastModifiedDate(curDate);
					user.setLastLoginDt(curDate);

					TOKEN_ACTION_LOGIC.updateRestTokenPingTime(authToken);	// Update the last pinged time.
					if(user.isSuperUser()) {		// All resources are allowed for super user.
						return true;
					} else if(XoUtil.hasData(permissions)){
						Set<PermissionEnum> userCurrentPermissions = user.getPermissionEnums();
						List<PermissionEnum> requestedPermissions = Arrays.asList(permissions);
						return XoUtil.hasData(permissions) && userCurrentPermissions.containsAll(requestedPermissions);
					} else {
						return true;
					}
				}
			}
		}
		return false;
	}

}
