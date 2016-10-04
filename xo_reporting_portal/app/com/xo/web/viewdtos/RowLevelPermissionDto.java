package com.xo.web.viewdtos;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.xo.web.models.system.Permission;
import com.xo.web.models.system.RolePermission;
import com.xo.web.models.system.RolePermissionResourceInstance;
import com.xo.web.models.system.UserPermission;
import com.xo.web.models.system.UserPermissionResourceInstance;

@SuppressWarnings("serial")
public class RowLevelPermissionDto extends BaseDto<RowLevelPermissionDto> {

	// Role based RLP
	public Integer rolePermissionId;
	public Integer roleId;

	// User based RLP
	public Integer userPermissionId;
	public Integer userId;

	// Common properties of RLP
	public Integer permissionId;
	public Integer resourceTypeId;
	public String entityId;
	public String displayText; 
	public boolean active;
	public String resourceTypeName;

	public RowLevelPermissionDto() {
	}

	public RowLevelPermissionDto(RolePermissionResourceInstance rolePermissionResourceInstance ) {
		if(rolePermissionResourceInstance != null) {

			final RolePermission rolePermission = rolePermissionResourceInstance.getRolePermission();
			this.rolePermissionId = rolePermission.getRolePermissionId();
			this.roleId = rolePermission.getRole().getRoleId();

			final Permission permission = rolePermission.getPermission();
			this.entityId = rolePermissionResourceInstance.getRolePermissionResourceInstanceId().getResourceInstanceId();
			this.resourceTypeId = permission.getResourceType().getResourceTypeId();
			this.resourceTypeName = permission.getResourceType().getName();
			this.displayText = rolePermissionResourceInstance.getDisplayText();
			this.permissionId = permission.getPermissionId();
			this.active = rolePermissionResourceInstance.isActive();
		}
	}

	public RowLevelPermissionDto(UserPermissionResourceInstance userPermissionResourceInstance) {
		if(userPermissionResourceInstance != null) {

			UserPermission userPermission = userPermissionResourceInstance.getUserPermission();
			this.userPermissionId = userPermission.getUserPermissionId();
			this.userId = userPermission.getUser().getUserId();

			final Permission permission = userPermission.getPermission();
			this.resourceTypeName = permission.getResourceType().getName();
			this.resourceTypeId = permission.getResourceType().getResourceTypeId();
			this.permissionId = permission.getPermissionId();
			this.active = userPermissionResourceInstance.isActive();
			this.entityId = userPermissionResourceInstance.getId().getResourceInstanceId();
			this.displayText = userPermissionResourceInstance.getDisplayText();
		}
	}

	@Override
    public int hashCode() {
		return new HashCodeBuilder().append(this.rolePermissionId).
				append(this.entityId).
				append(this.displayText).
				append(this.resourceTypeId).
				append(this.roleId).
				append(this.permissionId).toHashCode();
    }

    public boolean equals(Object object) {
        if (!(object instanceof RowLevelPermissionDto)) {
            return false;
        }
        RowLevelPermissionDto other = (RowLevelPermissionDto) object;
        if ((this.rolePermissionId == null && other.rolePermissionId != null) || (this.rolePermissionId != null && !this.rolePermissionId.equals(other.rolePermissionId))) {
            return false;
        }
        if ((this.userPermissionId == null && other.userPermissionId != null) || (this.userPermissionId != null && !this.userPermissionId.equals(other.userPermissionId))) {
            return false;
        }
        if ((this.entityId == null && other.entityId != null) || (this.entityId != null && !this.entityId.equals(other.entityId))) {
            return false;
        }
        return true;
    }
}
