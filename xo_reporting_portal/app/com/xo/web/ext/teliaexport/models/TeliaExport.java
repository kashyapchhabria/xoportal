package com.xo.web.ext.teliaexport.models;

import java.util.Date;

import com.xo.web.models.system.AbstractEntity;
import com.xo.web.models.system.User;

public class TeliaExport extends AbstractEntity {
	
	 private Integer exportId;
     private Date createdDate;
     private User user;
     public String dateOfEvent;
     public Integer noOfUsers;
    
     public TeliaExport() {
     }
     
     public TeliaExport(Date createdDate,User user,String dateOfEvent,Integer noOfUsers){
		this.createdDate = createdDate;
		this.user = user;
		this.dateOfEvent=dateOfEvent;
		this.noOfUsers=noOfUsers;
    }
    
    public Integer getExportId() {
        return this.exportId;
    }
    
    public void setExportId(Integer id) {
        this.exportId = id;
    }
    
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User users) {
        this.user = users;
    }

	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	public String getDateOfEvent() {
		return this.dateOfEvent;
	}
	public void setDateOfEvent(String dateOfEvent) {
		this.dateOfEvent = dateOfEvent;
	}
	
	public Integer getnoOfUsers() {
		return this.noOfUsers;
	}
	public void setnoOfUsers(Integer noOfUsers) {
		this.noOfUsers = noOfUsers;
	}

}
