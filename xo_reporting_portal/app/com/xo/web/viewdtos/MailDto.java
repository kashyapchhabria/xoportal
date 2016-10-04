package com.xo.web.viewdtos;

import org.apache.commons.lang3.builder.HashCodeBuilder;


@SuppressWarnings("serial")
public class MailDto extends BaseDto<MailDto> {

	public String message;
	public String subject;
	public String[] emailIds;

	public MailDto() {
	}

	public MailDto(String message, String subject, String...emailIds) {
		this.message = message;
		this.subject = subject;
		this.emailIds = emailIds;
	}

	@Override
    public int hashCode() {
		return new HashCodeBuilder().append(this.message).
				append(this.subject).
				append(this.emailIds).toHashCode();
    }

    public boolean equals(Object object) {
        if (!(object instanceof MailDto)) {
            return false;
        }
        MailDto other = (MailDto) object;
        if ((this.emailIds == null && other.emailIds != null) || 
        		(this.emailIds != null && !this.emailIds.equals(other.emailIds))) {
            return false;
        }
        return true;
    }
}
