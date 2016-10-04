package com.xo.web.mgr;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.xo.web.core.XODAOException;
import com.xo.web.models.dao.RoleDAO;
import com.xo.web.models.dao.RoleDAOImpl;
import com.xo.web.models.dao.RolePermissionDAO;
import com.xo.web.models.dao.RolePermissionDAOImpl;
import com.xo.web.models.dao.RolesPermissionsResourceInstanceDAO;
import com.xo.web.models.dao.RolesPermissionsResourceInstanceDAOImpl;
import com.xo.web.models.system.Permission;
import com.xo.web.models.system.ResourceType;
import com.xo.web.models.system.Role;
import com.xo.web.models.system.RolePermission;
import com.xo.web.models.system.RolePermissionResourceInstance;
import com.xo.web.models.system.RolePermissionResourceInstanceId;
import com.xo.web.util.XoUtil;
import com.xo.web.viewdtos.RowLevelPermissionDto;

public class RolePermissionResourceInstanceLogic extends RowLevelPermissionLogic<RolePermissionResourceInstance, RolePermissionResourceInstanceId> {

	private final RolesPermissionsResourceInstanceDAO rolesPermissionsResourceInstanceDAO;
	private final RoleDAO roleDAO;
	private final RolePermissionDAO rolePermissionDAO;

	public RolePermissionResourceInstanceLogic() {
		super(new RolesPermissionsResourceInstanceDAOImpl());
		this.rolesPermissionsResourceInstanceDAO = (RolesPermissionsResourceInstanceDAO) this.entityDao;
		this.roleDAO = new RoleDAOImpl();
		this.rolePermissionDAO = new RolePermissionDAOImpl();
	}

	public void save(RowLevelPermissionDto rowLevelPermissionDto) throws XODAOException {
		if(rowLevelPermissionDto != null) {

			Collection<RolePermission> rolePermissions = this.rolePermissionDAO.findAllByRlpIDAndResourceTypeId(rowLevelPermissionDto.roleId, rowLevelPermissionDto.resourceTypeId);
			if(XoUtil.hasData(rolePermissions)) {
				for(RolePermission rolePermission : rolePermissions) {
					createRoleRLP(rowLevelPermissionDto, rolePermission);
				}
			} else {
				ResourceType resourceType = this.resourceTypeDao.find(rowLevelPermissionDto.resourceTypeId);
				Role role = this.roleDAO.find(rowLevelPermissionDto.roleId);
				Collection<Permission> resourcePermissions = resourceType.getPermissions();
				if(XoUtil.hasData(resourcePermissions)) {
					for(Permission permission : resourcePermissions) {
						RolePermission rolePermission = this.rolePermissionDAO.save(new RolePermission(role,permission));
						createRoleRLP(rowLevelPermissionDto, rolePermission);
					}
				}
			}
		}
	}

	private RolePermissionResourceInstance createRoleRLP(RowLevelPermissionDto rowLevelPermissionDto, RolePermission rolePermission) {
		RolePermissionResourceInstance rolePermissionResourceInstance;
		rolePermissionResourceInstance = new RolePermissionResourceInstance(rolePermission, rowLevelPermissionDto.entityId, rowLevelPermissionDto.displayText);
		rolePermissionResourceInstance.setCreatedDate(new Date());
		rolePermissionResourceInstance.setActive(true);
		rolePermissionResourceInstance = this.rolesPermissionsResourceInstanceDAO.save(rolePermissionResourceInstance);
		return rolePermissionResourceInstance;
	}

	protected Set<RowLevelPermissionDto> convertEntitiesToDtos(Collection<RolePermissionResourceInstance> rolePermissionResourceInstances) {
		Set<RowLevelPermissionDto> rolePermissionResourceInstanceDtos = new HashSet<RowLevelPermissionDto>();
		if(XoUtil.hasData(rolePermissionResourceInstances)) {
			for(RolePermissionResourceInstance rolePermissionResourceInstance : rolePermissionResourceInstances) {
				rolePermissionResourceInstanceDtos.add(new RowLevelPermissionDto(rolePermissionResourceInstance));
			}
		}
		return rolePermissionResourceInstanceDtos;
	}
}
