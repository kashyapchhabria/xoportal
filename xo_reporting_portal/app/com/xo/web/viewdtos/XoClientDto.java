package com.xo.web.viewdtos;

import com.xo.web.models.system.XoClient;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@SuppressWarnings("serial")
public class XoClientDto extends BaseDto<XoClientDto> {

	public Integer clientId;
    public String clientName;
    public String clientSecret;
    public boolean active;
    public boolean deleted;
    public String preferredTimeZone;

    public XoClientDto() {

    }

    public XoClientDto(XoClient xoClient) {
        this.clientId = xoClient.getClientId();
        this.clientName = xoClient.getName();
        this.clientSecret = xoClient.getSecret();
        this.active = xoClient.isActive();
        this.deleted = xoClient.isDeleted();
        this.preferredTimeZone = xoClient.getPreferredTimeZone();
    }

    public XoClient asEntityObject() {
    	return new XoClient(this.clientName, this.clientSecret);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().
                append(this.clientId).
                append(this.clientName).
                append(this.clientSecret).toHashCode();
    }


    public boolean equals(Object object) {
        if (!(object instanceof XoClientDto)) {
            return false;
        }
        XoClientDto other = (XoClientDto) object;
        if ((this.clientId == null && other.clientId != null) || 
        		(this.clientId != null && !this.clientId.equals(other.clientId) ||
        				(this.clientName != null && !this.clientName.equalsIgnoreCase(other.clientName)))) {
            return false;
        }
        return true;
    }

}
