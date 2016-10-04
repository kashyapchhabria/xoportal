package com.xo.web.mgr;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.xo.web.models.dao.PermissionDAO;
import com.xo.web.models.dao.PermissionDAOImpl;
import com.xo.web.models.dao.UserDAO;
import com.xo.web.models.dao.UserDAOImpl;
import com.xo.web.models.dao.UserPermissionDAO;
import com.xo.web.models.dao.UserPermissionDAOImpl;
import com.xo.web.models.system.Permission;
import com.xo.web.models.system.User;
import com.xo.web.models.system.UserPermission;
import com.xo.web.util.XoUtil;
import com.xo.web.viewdtos.UserPermissionDto;

public class UserPermissionLogic extends BaseLogic<UserPermission, Integer>{

	private final UserPermissionDAO userPermissionDAO;
	private final UserDAO userDao;
	private final PermissionDAO permissionDao;
	
	public UserPermissionLogic() {
		super(new UserPermissionDAOImpl());
		this.userPermissionDAO = (UserPermissionDAO) entityDao;
		this.userDao = new UserDAOImpl();
		this.permissionDao = new PermissionDAOImpl();
	}

	public Set<UserPermissionDto> findAllUserPermissions(Integer userId) {
		Set<UserPermission> allUsers = null;
		if(userId != null) {
			User user = this.userDao.find(userId);
			if(user != null) {				
				allUsers = user.getUsersPermissions();
			}
		}
		return convertEntitiesToDtos(allUsers);
	}

	private Set<UserPermissionDto> convertEntitiesToDtos(Collection<UserPermission> allUserPermissions) {
		Set<UserPermissionDto> userDtos = new HashSet<UserPermissionDto>();
		if(XoUtil.hasData(allUserPermissions)) {
			for(UserPermission userPermission : allUserPermissions) {
				userDtos.add(new UserPermissionDto(userPermission));
			}
		}
		return userDtos;
	}

	public void toggleUserStatus(Integer userpermissionId) {
		if(userpermissionId > 0) {
			UserPermission userPermission = this.userPermissionDAO.find(userpermissionId);
			if(userPermission != null) {
				userPermission.setActive(!userPermission.isActive());
				this.userPermissionDAO.merge(userPermission);
			}
		}
	}

	public UserPermission create(Integer userId, Integer permissionId) {
		UserPermission userPermission = null;
		if(userId > 0  && permissionId > 0 ) {
			User user = this.userDao.find(userId);
			Permission permission = this.permissionDao.find(permissionId);
			userPermission = this.userPermissionDAO.save(new UserPermission(permission,user));			
			
		}
		return userPermission;
	}	

}