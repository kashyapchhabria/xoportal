package com.xo.web.models.system;
// Generated 30 May, 2015 2:15:28 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.xo.web.audit.Auditable;
import com.xo.web.viewdtos.KeyValueDTO;

/**
 * XoClient generated by hbm2java
 */
@SuppressWarnings("serial")
@Auditable
public class XoClient extends AbstractEntity {

    private Integer clientId;
    private String name;
    private String secret;
    private String preferredTimeZone;
	private Set<User> users = new HashSet<User>(0);
	private Set<XoClientJobConfig> clientJobsConfigurations = new HashSet<XoClientJobConfig>(0);

    public XoClient() {
    }

    public XoClient(String name, String secret) {
    	this.name = name;
    	this.secret = secret;
    }

    public String getPreferredTimeZone() {
        return preferredTimeZone;
    }

    public void setPreferredTimeZone(String preferredTimeZone) {
        this.preferredTimeZone = preferredTimeZone;
    }

    public Integer getClientId() {
        return this.clientId;
    }
    
    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String clientName) {
        this.name = clientName;
    }

    public Set<XoClientJobConfig> getClientJobsConfigurations() {
        return this.clientJobsConfigurations;
    }
    
    public void setClientJobsConfigurations(Set<XoClientJobConfig> clientJobsConfigurationses) {
        this.clientJobsConfigurations = clientJobsConfigurationses;
    }

	@Override
    public int hashCode() {
		return new HashCodeBuilder().append(this.clientId).
				append(this.name).
				append(this.active).toHashCode();
    }

    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the userId fields are not set
        if (!(object instanceof XoClient)) {
            return false;
        }
        XoClient other = (XoClient) object;
        if ((this.clientId == null && other.clientId != null) || (this.clientId != null && !this.clientId.equals(other.clientId))) {
            return false;
        }
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public String toString() {
    	return this.name;
    }

    public KeyValueDTO asKeyValue() {
		return new KeyValueDTO(this.clientId, this.name);
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}
}


