package com.xo.web.models.system;

import org.apache.commons.lang3.builder.HashCodeBuilder;

// Generated 18 Nov, 2014 11:54:37 AM by Hibernate Tools 4.3.1



/**
 * RolePermissionResourceInstanceId generated by hbm2java
 */
@SuppressWarnings("serial")
public class RolePermissionResourceInstanceId  implements java.io.Serializable {


     private Integer rolePermissionId;
     private String resourceInstanceId;

    public RolePermissionResourceInstanceId() {
    }

    public RolePermissionResourceInstanceId(Integer rolesPermissionsId, String resourceInstanceId) {
       this.rolePermissionId = rolesPermissionsId;
       this.resourceInstanceId = resourceInstanceId;
    }
   
    public Integer getRolePermissionId() {
        return this.rolePermissionId;
    }
    
    public void setRolePermissionId(Integer rolesPermissionsId) {
        this.rolePermissionId = rolesPermissionsId;
    }
    public String getResourceInstanceId() {
        return this.resourceInstanceId;
    }
    
    public void setResourceInstanceId(String resourceInstanceId) {
        this.resourceInstanceId = resourceInstanceId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof RolePermissionResourceInstanceId) ) return false;
		 RolePermissionResourceInstanceId castOther = ( RolePermissionResourceInstanceId ) other; 
         
		 return (this.getRolePermissionId()==castOther.getRolePermissionId())
 && ( (this.getResourceInstanceId()==castOther.getResourceInstanceId()) || ( this.getResourceInstanceId()!=null && castOther.getResourceInstanceId()!=null && this.getResourceInstanceId().equals(castOther.getResourceInstanceId()) ) );
   }
   
   public int hashCode() {
         return new HashCodeBuilder().append(this.rolePermissionId).
        		 append(this.resourceInstanceId).toHashCode();
   }   


}


