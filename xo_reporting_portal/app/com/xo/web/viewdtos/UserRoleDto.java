package com.xo.web.viewdtos;

import com.xo.web.models.system.UserRole;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@SuppressWarnings("serial")
public class UserRoleDto extends BaseDto<UserRoleDto> {

	public Integer userId;
	public Integer roleId;
	public String name;
	public boolean active;

	public UserRoleDto(){
	}

	public UserRoleDto(UserRole userRole) {
		this.userId = userRole.getUser().getUserId();
		this.roleId = userRole.getRole().getRoleId();
		this.name = userRole.getRole().getName();
		this.active = userRole.isActive();
	}

	@Override
    public int hashCode() {
		return new HashCodeBuilder().append(this.userId).
				append(this.roleId).
				append(this.name).toHashCode();
    }

    public boolean equals(Object object) {
        if (!(object instanceof UserRoleDto)) {
            return false;
        }
        UserRoleDto other = (UserRoleDto) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        if ((this.roleId == null && other.roleId != null) || (this.roleId != null && !this.roleId.equals(other.roleId))) {
            return false;
        }
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equalsIgnoreCase(other.name))) {
            return false;
        }
        return true;
    }
}
