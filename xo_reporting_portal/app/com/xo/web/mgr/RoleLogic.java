package com.xo.web.mgr;

import com.xo.web.core.XODAOException;
import com.xo.web.core.XOException;
import com.xo.web.models.dao.RoleDAO;
import com.xo.web.models.dao.RoleDAOImpl;
import com.xo.web.models.dao.RolePermissionDAO;
import com.xo.web.models.dao.UserRoleDAO;
import com.xo.web.models.dao.UserRoleDAOImpl;
import com.xo.web.models.dao.RolePermissionDAOImpl;
import com.xo.web.models.system.Role;
import com.xo.web.models.system.RoleEnum;
import com.xo.web.models.system.RolePermission;
import com.xo.web.models.system.User;
import com.xo.web.models.system.UserRole;
import com.xo.web.util.XoUtil;
import com.xo.web.viewdtos.RoleDto;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.List;


public class RoleLogic extends BaseLogic<Role, Integer>{

	private final RoleDAO roleDAO;
    private final UserRoleDAO userRoleDAO;
    private final RolePermissionDAO rolePermissionDAO;

	public RoleLogic() {
		super(new RoleDAOImpl());
		this.roleDAO = (RoleDAO) entityDao;
        this.userRoleDAO = new UserRoleDAOImpl();
        this.rolePermissionDAO = new RolePermissionDAOImpl();
	}

	public Map<Integer, String> getActiveRolesMap()  throws XODAOException{
		Map<Integer, String> activeRoles = new HashMap<Integer, String>();
		Collection<Role> availableActiveRoles = this.roleDAO.findByActive(true);
		if(XoUtil.hasData(availableActiveRoles)) {
			for(Role role : availableActiveRoles) {
				activeRoles.put(role.getRoleId(), role.getName());
			}
		}
		return activeRoles;
	}

	public final boolean isAdmin(User user) {
		return this.hasRole(user, RoleEnum.Admin);
	}

	public final boolean hasRole(User user, RoleEnum roleEnum) {

		if(user != null && user.isActive() && roleEnum != null) {
			Set<String> userRoleNames = user.getMyRoleNames();	// All resources are allowed for Admin users. 
			if(XoUtil.hasData(userRoleNames) &&  userRoleNames.contains(roleEnum.name())) {
				return true;
			}
		}
		return false;
	}

	public Set<RoleDto> findAllRoles() {
		Collection<Role> allRoles = this.roleDAO.findAll();
		return this.convertEntitiesToDtos(allRoles);
	}

	public Set<RoleDto> readUnassigned(Integer userId) throws XODAOException {
		Set<RoleDto> roleDtos = new HashSet<RoleDto>();
		roleDtos = null;
		if(userId > 0) {
			List<Role> allRoles = this.roleDAO.findUnassignedRoles(userId);
			roleDtos = this.convertEntitiesToDtos(allRoles);
		}
		return roleDtos ;
	}

	private Set<RoleDto> convertEntitiesToDtos(Collection<Role> allRoles) {
		Set<RoleDto> roleDtos = new HashSet<RoleDto>();
		if(XoUtil.hasData(allRoles)) {
			for(Role role : allRoles) {
				roleDtos.add(new RoleDto(role));
			}
		}
		return roleDtos;
	}

	public boolean validateRoleDetails(RoleDto roleDto) throws XOException {
		boolean isValid = false;
		if(roleDto != null) {
			if(XoUtil.isNotNull(roleDto.name, roleDto.description)) {
				Role role = this.roleDAO.findByName(roleDto.name);
				if(role != null && roleDto.roleId != role.getRoleId()) {
					String message= "Role "+roleDto.name+" already exists.";
					throw new XOException(message);
				} else {
					isValid = true;
				}
			}
		}
		return isValid;
	}


	public Role save(RoleDto roleDto) throws XODAOException {
		Role role = null;
		if(roleDto != null) {
			role = roleDto.asEntityObject();
			role.setActive(true);
			role = super.save(role);
		}
		return role;
	}

	public RoleDto read(Integer id) {
		RoleDto roleDto = null;
		if(id > 0) {
			Role role = this.roleDAO.find(id);
			if(role != null) {
				roleDto = new RoleDto(role);
			}
		}
		return roleDto;
	}

	public void update(RoleDto roleDto) throws XODAOException {
		if(roleDto != null) {
			Role role = this.find(roleDto.roleId);
			if(role != null) {
				role.setName(roleDto.name);
				role.setDescription(roleDto.description);
				super.update(role);
			}
		}
	}

    public void delete(Integer roleId) {
        if(roleId > 0) {
            Role role = this.find(roleId);
            if(role != null) {
                Set<UserRole> userRoles = role.getUserRoles();
                Set<RolePermission> rolePermissions = role.getRolePermissions();

                if(XoUtil.hasData(userRoles)) {
                    for(UserRole userRole : userRoles) {
                        this.userRoleDAO.remove(userRole);
                    }
                }
                if(XoUtil.hasData(rolePermissions)) {
                    for(RolePermission rolePermission : rolePermissions) {
                        this.rolePermissionDAO.remove(rolePermission);
                    }
                }
                this.roleDAO.remove(role);
            }
        }
    }

}
