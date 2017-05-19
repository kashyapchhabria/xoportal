package com.xo.web.models.system;

import java.util.Date;

import com.xo.web.audit.Auditable;



/**
 * TokenAction generated by hbm2java
 */
@SuppressWarnings("serial")
@Auditable
public class TokenAction  extends AbstractEntity {

     private Integer tokenActionId;
     private User user;
     private String token;
     private TokenType type;
     private Date expiresDate;

    public TokenAction() {
    }

    public Integer getTokenActionId() {
        return this.tokenActionId;
    }
    
    public void setTokenActionId(Integer id) {
        this.tokenActionId = id;
    }
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User users) {
        this.user = users;
    }
    public String getToken() {
        return this.token;
    }
    
    public void setToken(String token) {
        this.token = token;
    }

    public TokenType getType() {
		return type;
	}

	public void setType(TokenType type) {
		this.type = type;
	}

	public Date getExpiresDate() {
		return expiresDate;
	}

	public void setExpiresDate(Date expiresDate) {
		this.expiresDate = expiresDate;
	}

	public boolean isValid() {
		return this.expiresDate.after(new Date());
	}

	public String toString() {
		return this.type.name();
	}

}


