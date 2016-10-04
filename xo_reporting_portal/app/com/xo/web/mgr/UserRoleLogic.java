package com.xo.web.mgr;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.xo.web.core.XODAOException;
import com.xo.web.models.dao.RoleDAO;
import com.xo.web.models.dao.RoleDAOImpl;
import com.xo.web.models.dao.UserDAO;
import com.xo.web.models.dao.UserDAOImpl;
import com.xo.web.models.dao.UserRoleDAO;
import com.xo.web.models.dao.UserRoleDAOImpl;
import com.xo.web.models.system.Role;
import com.xo.web.models.system.User;
import com.xo.web.models.system.UserRole;
import com.xo.web.models.system.UserRoleId;
import com.xo.web.util.XoUtil;
import com.xo.web.viewdtos.UserRoleDto;

public class UserRoleLogic extends BaseLogic<UserRole, UserRoleId>{

	private final UserRoleDAO userRoleDAO;
	private final UserDAO userDao;
	private final RoleDAO roleDao;

	public UserRoleLogic() {
		super(new UserRoleDAOImpl());
		this.userRoleDAO = (UserRoleDAO) entityDao;
		this.userDao = new UserDAOImpl();
		this.roleDao = new RoleDAOImpl();
	}

	public Set<UserRoleDto> findAllUserRoles(Integer userId) {
		Set<UserRole> allUsers = null;
		if(userId != null) {
			User user = this.userDao.find(userId);
			if(user != null) {
				allUsers = user.getUserRoles();
			}
		}
		return this.convertEntitiesToDtos(allUsers);
	}

	private Set<UserRoleDto> convertEntitiesToDtos(Collection<UserRole> allUserRoles) {
		Set<UserRoleDto> userDtos = new HashSet<UserRoleDto>();
		if(XoUtil.hasData(allUserRoles)) {
			for(UserRole userRole : allUserRoles) {
				userDtos.add(new UserRoleDto(userRole));
			}
		}
		return userDtos;
	}

	public Role create(Integer userId, Integer roleId) {
		Role role = null;
		if(userId > 0  && roleId > 0 ) {
			User user = this.userDao.find(userId);
			role = this.roleDao.find(roleId);
			this.userRoleDAO.save(new UserRole(role,user));
		}
		return role;
	}

    public Integer readRolesCount(Integer roleId) throws XODAOException {
        List<UserRole> allRoleUsers = null;
        int count = 0;
        if(roleId != null){
            Role role = this.roleDao.find(roleId);
            count = XoUtil.hasData(role.getUserRoles()) ? role.getUserRoles().size() : 0;
        }
        return count;
    }
}
