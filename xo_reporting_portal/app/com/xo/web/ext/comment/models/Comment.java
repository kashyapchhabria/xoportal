package com.xo.web.ext.comment.models;

import java.util.Date;

import com.xo.web.models.system.AbstractEntity;
import com.xo.web.models.system.User;

@SuppressWarnings("serial")
public class Comment extends AbstractEntity {
	
	 private Integer messageId;
     private String message;
     private Date ts;
     private User user;
     public String sheetName;
     public String dashboardName;
    
     public Comment() {
     }
     
     public Comment(String message,Date ts,User user,String sheetName,String dashboardName){
		this.message =message;
		this.ts = ts;
		this.user = user;
		this.sheetName=sheetName;
		this.dashboardName=dashboardName;
    }
    
    public Integer getMessageId() {
        return this.messageId;
    }
    
    public void setMessageId(Integer id) {
        this.messageId = id;
    }
    
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User users) {
        this.user = users;
    }

    public String getMessage() {
		return this.message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public Date getTs() {
		return ts;
	}
	public void setTs(Date ts) {
		this.ts = ts;
	}
	
	public String getSheetName() {
		return this.sheetName;
	}
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
	
	public String getDashboardName() {
		return this.dashboardName;
	}
	public void setDashboardName(String dashboardName) {
		this.dashboardName = dashboardName;
	}

}
