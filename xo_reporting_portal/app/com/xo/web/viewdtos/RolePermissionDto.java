package com.xo.web.viewdtos;

import com.xo.web.models.system.RolePermission;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@SuppressWarnings("serial")
public class RolePermissionDto extends BaseDto<RolePermissionDto> {

	public Integer rolePermissionId;
	public Integer permissionId;
    public Integer roleId;
    public String p_name;
    public String r_name;    
    public boolean active;
    
	public RolePermissionDto() {
	}

	public RolePermissionDto(RolePermission rolePermission) {
		this.rolePermissionId=rolePermission.getRolePermissionId();
        this.active = rolePermission.isActive();
		this.roleId = rolePermission.getRole().getRoleId();
		this.permissionId = rolePermission.getPermission().getPermissionId();
		this.p_name=rolePermission.getPermission().getName();
		this.r_name=rolePermission.getRole().getName();
	}

	@Override
    public int hashCode() {
    	return new HashCodeBuilder().
        		append(this.roleId).
        		append(this.permissionId).
        		append(this.p_name).
        		append(this.r_name).
                append(this.active).toHashCode();
    }


    public boolean equals(Object object) {
        if (!(object instanceof RolePermissionDto)) {
            return false;
        }
        RolePermissionDto other = (RolePermissionDto) object;
        if ((this.permissionId == null && other.permissionId != null) || (this.permissionId != null && !this.permissionId.equals(other.permissionId))) {
            return false;
        }
        if ((this.roleId == null && other.roleId != null) || (this.roleId != null && !this.roleId.equals(other.roleId))) {
            return false;
        }
        if ((this.p_name == null && other.p_name != null) || (this.p_name != null && !this.p_name.equalsIgnoreCase(other.p_name))) {
            return false;
        }
        if ((this.r_name == null && other.r_name != null) || (this.r_name != null && !this.r_name.equalsIgnoreCase(other.r_name))) {
            return false;
        }
        return true;
    }
	
}
