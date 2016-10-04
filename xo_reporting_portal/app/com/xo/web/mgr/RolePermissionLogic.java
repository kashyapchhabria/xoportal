package com.xo.web.mgr;

import java.util.Collection;
import java.util.Set;
import java.util.HashSet;

import com.xo.web.models.dao.RolePermissionDAO;
import com.xo.web.models.dao.RolePermissionDAOImpl;
import com.xo.web.models.dao.RoleDAO;
import com.xo.web.models.dao.RoleDAOImpl;
import com.xo.web.models.dao.PermissionDAO;
import com.xo.web.models.dao.PermissionDAOImpl;
import com.xo.web.models.system.Permission;
import com.xo.web.models.system.RolePermission;
import com.xo.web.models.system.Role;
import com.xo.web.util.XoUtil;
import com.xo.web.viewdtos.RolePermissionDto;

public class RolePermissionLogic extends BaseLogic<RolePermission,Integer >{

	private final RolePermissionDAO rolePermissionDAO;
	private final RoleDAO roleDAO;
    private final PermissionDAO permissionDAO;

	public RolePermissionLogic() {
		super(new RolePermissionDAOImpl());
		this.rolePermissionDAO = (RolePermissionDAO) entityDao;
		this.roleDAO = new RoleDAOImpl();
        this.permissionDAO = new PermissionDAOImpl();
	}

	public Set<RolePermissionDto> findAllRolesPermission(Integer roleId) {
		Set<RolePermission> allPermissions = null;
		if(roleId != null) {
			Role role = this.roleDAO.find(roleId);
			if(role != null) {				
				allPermissions = role.getRolePermissions();
			}
		}
		return this.convertEntitiesToDtos(allPermissions);
	}

    public Set<RolePermissionDto> findAllUnAvailableRolesPermission(Integer roleId) {
        Set<RolePermission> allPermissions = null;
        if(roleId > 0) {
            Role role = this.roleDAO.find(roleId);
            if(role != null) {
                allPermissions = role.getRolePermissions();
            }
        }
        return this.convertEntitiesToDtos(allPermissions);
    }

	private Set<RolePermissionDto> convertEntitiesToDtos(Collection<RolePermission> allPermissions) {
		Set<RolePermissionDto> rolePermissionDtos = new HashSet<RolePermissionDto>();
		if(XoUtil.hasData(allPermissions)) {
			for(RolePermission rolePermission : allPermissions) {
				rolePermissionDtos.add(new RolePermissionDto(rolePermission));
			}
		}
		return rolePermissionDtos;
	}

    public RolePermission create(Integer roleId, Integer permissionId) {
    	RolePermission rolePermission =null;
            if (roleId > 0 &&  permissionId > 0) {
                Role role = this.roleDAO.find(roleId);
                Permission permission = this.permissionDAO.find(permissionId);
                rolePermission = this.rolePermissionDAO.save(new RolePermission(role,permission));
            }
            return rolePermission;
    }

}
