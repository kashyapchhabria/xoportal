package com.xo.web.viewdtos;

import com.xo.web.models.system.User;
import com.xo.web.models.system.XoClient;
import com.xo.web.util.XoUtil;

import org.apache.commons.lang3.builder.HashCodeBuilder;

@SuppressWarnings("serial")
public class UserDto extends BaseDto<UserDto> {

	public Integer userId;
	public String firstName;
	public String secondName;
	public String email;
	public String encryptedPassword;
	public String authToken;
	public boolean isEmailVerified;
	public String lastLoginDt;
	public boolean active;
	public boolean deleted;
	public Integer clientId;
	public String clientName;
	public String clientSecret;

	public UserDto(){
	}

	public UserDto(String firstName, String secondName, String email, String clientName){
		this.firstName = firstName;
		this.secondName = secondName;
		this.email = email;
		this.clientName = clientName;
	}

	public UserDto(User user) {
		if(user != null) {
			this.active = user.isActive();
			this.userId = user.getUserId();
			this.firstName = user.getFirstName();
			this.secondName = user.getSecondName();
			this.email = user.getEmail();
			this.isEmailVerified = user.isIsEmailVerified();
			this.deleted = user.isDeleted();
			this.lastLoginDt = user.getLastLoginDt() != null ? XoUtil.formatDate(user.getLastLoginDt()) : "";
			final XoClient xoClient = user.getXoClient();
			if(xoClient != null) {
				this.clientId = xoClient.getClientId();
				this.clientName = xoClient.getName();
				this.clientSecret = xoClient.getSecret();
			}
			this.encryptedPassword = user.getPassword();
		}
	}

	public User asEntityObject() {
		return new User(this.firstName, this.secondName, this.email);
	}

	@Override
    public int hashCode() {
		return new HashCodeBuilder().append(this.userId).
				append(this.firstName).
				append(this.secondName).
				append(this.email).
				append(this.active).toHashCode();
    }

    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the userId fields are not set
        if (!(object instanceof UserDto)) {
            return false;
        }
        UserDto other = (UserDto) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        if ((this.email == null && other.email != null) || (this.email != null && !this.email.equalsIgnoreCase(other.email))) {
            return false;
        }
        return true;
    }
}
