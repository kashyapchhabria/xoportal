package com.xo.web.viewdtos;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.xo.web.models.system.UserPermission;

@SuppressWarnings("serial")
public class UserPermissionDto extends BaseDto<UserPermissionDto> {

    public Integer userPermissionId;
	public Integer userId;
	public Integer permissionId;
	public String name;
	public boolean active;

	public UserPermissionDto(){
	}

	public UserPermissionDto(UserPermission userPermission) {
        this.userPermissionId=userPermission.getUserPermissionId();
		this.userId = userPermission.getUser().getUserId();
		this.permissionId = userPermission.getPermission().getPermissionId();
		this.name = userPermission.getPermission().getName();
		this.active = userPermission.isActive();
	}

	@Override
    public int hashCode() {
		return new HashCodeBuilder().append(this.userId).
				append(this.permissionId).
				append(this.name).toHashCode();
    }

    public boolean equals(Object object) {
        if (!(object instanceof UserPermissionDto)) {
            return false;
        }
        UserPermissionDto other = (UserPermissionDto) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        if ((this.permissionId == null && other.permissionId != null) || (this.permissionId != null && !this.permissionId.equals(other.permissionId))) {
            return false;
        }
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equalsIgnoreCase(other.name))) {
            return false;
        }
        return true;
    }
}
