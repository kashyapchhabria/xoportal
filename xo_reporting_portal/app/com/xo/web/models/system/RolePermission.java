package com.xo.web.models.system;

import java.util.HashSet;
import java.util.Set;
// Generated 22-Oct-2014 01:24:44 by Hibernate Tools 4.3.1


import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.xo.web.viewdtos.KeyValueDTO;



/**
 * RolePermission generated by hbm2java
 */
@SuppressWarnings("serial")
public class RolePermission extends AbstractEntity {

	private Integer rolePermissionId;
    private Permission permission;
    private Role role;
    private Set<RolePermissionResourceInstance> rolePermissionResourceInstances = new HashSet<RolePermissionResourceInstance>(0);

    public RolePermission() {
    }

    public RolePermission(Role role, Permission permission) {
        this.permission = permission;
        this.role = role;
        this.active = true;
    }

    public Integer getRolePermissionId() {
        return this.rolePermissionId;
    }

    public void setRolePermissionId(Integer id) {
        this.rolePermissionId = id;
    }
    public Permission getPermission() {
        return this.permission;
    }
    
    public void setPermission(Permission permissions) {
        this.permission = permissions;
    }
    public Role getRole() {
        return this.role;
    }
    
    public void setRole(Role roles) {
        this.role = roles;
    }

    @Override
    public int hashCode() {
    	return new HashCodeBuilder().
        		append(this.rolePermissionId).
        		append(this.permission).
        		append(this.role).toHashCode();
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the rolePermissionId fields are not set
        if (!(object instanceof RolePermission)) {
            return false;
        }
        RolePermission other = (RolePermission) object;
        if ((this.rolePermissionId == null && other.rolePermissionId != null) || (this.rolePermissionId != null && !this.rolePermissionId.equals(other.rolePermissionId))) {
            return false;
        }
        return true;
    }

	public Set<RolePermissionResourceInstance> getRolePermissionResourceInstances() {
		return rolePermissionResourceInstances;
	}

	public void setRolePermissionResourceInstances(
			Set<RolePermissionResourceInstance> rolePermissionResourceInstances) {
		this.rolePermissionResourceInstances = rolePermissionResourceInstances;
	}

	public String toString() {
		return "Role:" + this.role.getName() + ", Permission:" + this.permission.getName(); 
	}

	public KeyValueDTO asKeyValue() {
		return new KeyValueDTO(this.rolePermissionId, this.toString());
	}
}


