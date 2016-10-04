package com.xo.web.viewdtos;

import com.xo.web.models.system.Role;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@SuppressWarnings("serial")
public class RoleDto extends BaseDto<RoleDto> {

	public Integer roleId;
	public String name;
	public String description;
    public boolean active;
    
	public RoleDto() {
	}

	public RoleDto(Role role) {
        this.active = role.isActive();
		this.description = role.getDescription();
		this.name = role.getName();
		this.roleId = role.getRoleId();
	}

	public Role asEntityObject() {
		return new Role(this.name, this.description);
	}

	@Override
    public int hashCode() {
    	return new HashCodeBuilder().
        		append(this.roleId).
        		append(this.name).
        		append(this.description).
                append(this.active).toHashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof RoleDto)) {
            return false;
        }
        RoleDto other = (RoleDto) object;
        if ((this.roleId == null && other.roleId != null) || 
        		(this.roleId != null && !this.roleId.equals(other.roleId) ||
        				(this.name != null && !this.name.equalsIgnoreCase(other.name)))) {
            return false;
        }
        return true;
    }
}
