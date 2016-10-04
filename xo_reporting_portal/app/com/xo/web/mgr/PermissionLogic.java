package com.xo.web.mgr;

import com.xo.web.core.XODAOException;
import com.xo.web.models.dao.PermissionDAO;
import com.xo.web.models.dao.PermissionDAOImpl;
import com.xo.web.models.system.Permission;
import com.xo.web.util.XoUtil;
import com.xo.web.viewdtos.PermissionDto;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PermissionLogic extends BaseLogic<Permission, Integer>{

    private final PermissionDAO permissionDAO;

    public PermissionLogic() {
        super(new PermissionDAOImpl());
        this.permissionDAO = (PermissionDAO) entityDao;
    }

    public Set<PermissionDto> findAllPermissions() {
        Collection<Permission> allPermissions = this.findAll();
        return convertEntitiesToDtos(allPermissions);
    }

	public Set<PermissionDto> readUnassigned(Integer userId) throws XODAOException {
		Set<PermissionDto> permissionDtos = null;
		if(userId > 0){
		List<Permission> allPermissions = this.permissionDAO.findUnassignedPermissions(userId);
		permissionDtos = convertEntitiesToDtos(allPermissions);
		}		
		return permissionDtos ;
	}
	
    public Set<PermissionDto> findUnassignedPermissions(Integer roleId) throws XODAOException {
        Set<PermissionDto> permissiondtos = null;
        if(roleId != null){
        List<Permission> allunPermissions = this.permissionDAO.findAllUnAvailablePermission(roleId);
            permissiondtos= this.convertEntitiesToDtos(allunPermissions);
        }
        return permissiondtos;
    }

    private Set<PermissionDto> convertEntitiesToDtos(Collection<Permission> allPermissions) {
        Set<PermissionDto> permissionDtos = new HashSet<PermissionDto>();
        if(XoUtil.hasData(allPermissions)) {
            for(Permission permission : allPermissions) {
                permissionDtos.add(new PermissionDto(permission));
            }
        }
        return permissionDtos;
    }

}