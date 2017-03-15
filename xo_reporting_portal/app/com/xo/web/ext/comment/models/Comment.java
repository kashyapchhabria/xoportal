package com.xo.web.ext.comment.models;

import java.util.Date;

import com.xo.web.models.system.AbstractEntity;
import com.xo.web.models.system.User;

public class Comment extends AbstractEntity {
	
	 private Integer messageId;
     private String message;
     private Date ts;
     private User user;
    
     public Comment() {
     }
     
     public Comment(String message,Date ts,User user){
		this.message =message;
		this.ts = ts;
		this.user = user;
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

}
