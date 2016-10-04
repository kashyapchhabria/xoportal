package com.xo.web.viewdtos;

import com.xo.web.models.system.ClientJobsConfigurationsId;
import com.xo.web.models.system.XoClientJobConfig;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@SuppressWarnings("serial")
public class ClientJobsConfigDto extends BaseDto<ClientJobsConfigDto> {

    public Integer configId;
    public Integer jobId;
    public Integer clientId;

    public ClientJobsConfigDto() {
    }

    public ClientJobsConfigDto(XoClientJobConfig xoClientJobConfig) {
    	if(xoClientJobConfig != null) {
    		ClientJobsConfigurationsId clientJobsConfigurationsId  = xoClientJobConfig.getClientJobsConfigurationsId();
    		this.configId= clientJobsConfigurationsId.getConfigInstanceId();
    		this.jobId = clientJobsConfigurationsId.getJobId();
    		this.clientId = clientJobsConfigurationsId.getClientId();
    	}
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().
                append(this.configId).
                append(this.jobId).
                append(this.clientId).toHashCode();
    }


    public boolean equals(Object object) {
        if (!(object instanceof ClientJobsConfigDto)) {
            return false;
        }
        ClientJobsConfigDto other = (ClientJobsConfigDto) object;
        if ((this.configId == null && other.configId != null) || (this.configId != null && !this.configId.equals(other.configId))) {
            return false;
        }
        if ((this.jobId == null && other.jobId != null) || (this.jobId != null && !this.jobId.equals(other.jobId))) {
            return false;
        }
        if ((this.clientId == null && other.clientId != null) || (this.clientId != null && !this.clientId.equals(other.clientId))) {
            return false;
        }
        return true;
    }
}

