package com.xo.web.viewdtos;

import com.xo.web.models.system.Permission;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@SuppressWarnings("serial")
public class PermissionDto extends BaseDto<PermissionDto> {

    public Integer permissionId;
    public String name;
    public String description;
    public boolean active;

    public PermissionDto() {
    }

    public PermissionDto(Permission permission) {
        this.active = permission.isActive();
        this.description = permission.getDescription();
        this.name = permission.getName();
        this.permissionId = permission.getPermissionId();
    }

    public Permission asEntityObject() {
        return new Permission(this.name, this.description);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().
                append(this.permissionId).
                append(this.name).
                append(this.description).
                append(this.active).toHashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PermissionDto)) {
            return false;
        }
        PermissionDto other = (PermissionDto) object;
        if ((this.permissionId == null && other.permissionId != null) ||
                (this.permissionId != null && !this.permissionId.equals(other.permissionId) ||
                        (this.name != null && !this.name.equalsIgnoreCase(other.name)))) {
            return false;
        }
        return true;
    }
}
