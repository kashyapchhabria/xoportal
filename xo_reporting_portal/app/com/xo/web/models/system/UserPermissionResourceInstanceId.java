package com.xo.web.models.system;

import org.apache.commons.lang3.builder.HashCodeBuilder;
// Generated 18 Nov, 2014 11:54:37 AM by Hibernate Tools 4.3.1

/**
 * UserPermissionResourceInstanceId generated by hbm2java
 */
@SuppressWarnings("serial")
public class UserPermissionResourceInstanceId  implements java.io.Serializable {

     private int userPermissionId;
     private String resourceInstanceId;

    public UserPermissionResourceInstanceId() {
    }

    public UserPermissionResourceInstanceId(int usersPermissionsId, String resourceInstanceId) {
       this.userPermissionId = usersPermissionsId;
       this.resourceInstanceId = resourceInstanceId;
    }
   
    public int getUserPermissionId() {
        return this.userPermissionId;
    }
    
    public void setUserPermissionId(int usersPermissionsId) {
        this.userPermissionId = usersPermissionsId;
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
		 if ( !(other instanceof UserPermissionResourceInstanceId) ) return false;
		 UserPermissionResourceInstanceId castOther = ( UserPermissionResourceInstanceId ) other; 

		 return (this.userPermissionId==castOther.userPermissionId)
				 && ( (this.resourceInstanceId==castOther.resourceInstanceId) || 
						 ( this.resourceInstanceId!=null && 
						 castOther.resourceInstanceId!=null && 
						 this.resourceInstanceId.equals(castOther.resourceInstanceId) ) );
   }

   public int hashCode() {
	   return new HashCodeBuilder().append(this.userPermissionId).
				append(this.resourceInstanceId).toHashCode();
   }   


}


