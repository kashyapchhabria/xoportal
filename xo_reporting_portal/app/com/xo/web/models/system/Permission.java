package com.xo.web.models.system;
// Generated 22-Oct-2014 01:24:44 by Hibernate Tools 4.3.1



import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.xo.web.audit.Auditable;
import com.xo.web.viewdtos.KeyValueDTO;

/**
 * Permission generated by hbm2java
 */
@SuppressWarnings("serial")
@Auditable
public class Permission  extends AbstractEntity {

     private Integer permissionId;
     private String name;
     private String description;
     private ResourceType resourceType;
     private Set<UserPermission> userPermissions = new HashSet<UserPermission>(0);
     private Set<RolePermission> rolePermissions = new HashSet<RolePermission>(0);

    public Permission() {
    }

    public Permission(String name, String description) {
    	this.name = name;
    	this.description = description;
    }
    
    public Integer getPermissionId() {
        return this.permissionId;
    }
    
    public void setPermissionId(Integer id) {
        this.permissionId = id;
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

    public Set<UserPermission> getUserPermissions() {
        return this.userPermissions;
    }
    
    public void setUserPermissions(Set<UserPermission> usersPermissionses) {
        this.userPermissions = usersPermissionses;
    }
    public Set<RolePermission> getRolePermissions() {
        return this.rolePermissions;
    }
    
    public void setRolePermissions(Set<RolePermission> rolesPermissionses) {
        this.rolePermissions = rolesPermissionses;
    }

    @Override
    public int hashCode() {
    	return new HashCodeBuilder().
        		append(this.permissionId).
        		append(this.name).
        		append(this.description).
        		append(this.resourceType).toHashCode();
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the permissionId fields are not set
        if (!(object instanceof Permission)) {
            return false;
        }
        Permission other = (Permission) object;
        if ((this.permissionId == null && other.permissionId != null) || (this.permissionId != null && !this.permissionId.equals(other.permissionId))) {
            return false;
        }
        return true;
    }

    public String toString() {
        return this.description;
    }

	public PermissionEnum getPermissionEnum() {
		return PermissionEnum.valueOf(this.name);
	}
	
	public ResourceType getResourceType() {
		return resourceType;
	}

	public void setResourceType(ResourceType resourceType) {
		this.resourceType = resourceType;
	}

	public KeyValueDTO asKeyValue() {
		return new KeyValueDTO(this.permissionId, this.name);
	}
}


