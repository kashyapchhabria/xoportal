package com.xo.web.models.system;
// Generated 22-Oct-2014 01:24:44 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.xo.web.viewdtos.KeyValueDTO;

/**
 * Role generated by hbm2java
 */
@SuppressWarnings("serial")
public class Role extends AbstractEntity {

     private Integer roleId;
     private String name;
     private String description;
     private Set<RolePermission> rolePermissions = new HashSet<RolePermission>(0);
     private Set<UserRole> userRoles = new HashSet<UserRole>(0);

    public Role() {
    }

    public Role(String name, String description) {
    	this.name = name;
    	this.description = description;
    }
    
    public Integer getRoleId() {
        return this.roleId;
    }
    
    public void setRoleId(Integer id) {
        this.roleId = id;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public Set<RolePermission> getRolePermissions() {
        return this.rolePermissions;
    }
    
    public void setRolePermissions(Set<RolePermission> rolesPermissionses) {
        this.rolePermissions = rolesPermissionses;
    }
    public Set<UserRole> getUserRoles() {
        return this.userRoles;
    }
    
    public void setUserRoles(Set<UserRole> usersRoleses) {
        this.userRoles = usersRoleses;
    }

    @Override
    public int hashCode() {
    	return new HashCodeBuilder().
        		append(this.roleId).
        		append(this.name).
        		append(this.description).toHashCode();
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the roleId fields are not set
        if (!(object instanceof Role)) {
            return false;
        }
        Role other = (Role) object;
        if ((this.roleId == null && other.roleId != null) || (this.roleId != null && !this.roleId.equals(other.roleId))) {
            return false;
        }
        return true;
    }

    public String toString() {
    	return this.name;
    }

    public KeyValueDTO asKeyValue() {
		return new KeyValueDTO(this.roleId, this.name);
	}
}


