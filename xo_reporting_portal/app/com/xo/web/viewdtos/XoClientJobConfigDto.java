package com.xo.web.viewdtos;

import com.xo.web.models.system.ClientJobsConfigurationsId;
import com.xo.web.models.system.XoClientJobConfig;
import com.xo.web.models.system.XoConfigInstance;
import com.xo.web.models.system.XoJob;

import org.apache.commons.lang3.builder.HashCodeBuilder;

@SuppressWarnings("serial")
public class XoClientJobConfigDto extends BaseDto<XoClientJobConfigDto> {

	public Integer configInstanceId;
	public String configName;
    public String configJson;
    public Integer jobId;
    public String jobName;
    public Integer clientId;
    public boolean isRunning;
    public boolean active;
    public boolean deleted;
    public String lastMessage;
    public String clientName;
    public String jobClassName;

    public XoClientJobConfigDto() {
    }

    public XoClientJobConfigDto(XoClientJobConfig xoClientJobConfig) {
    	if(xoClientJobConfig != null) {
    		ClientJobsConfigurationsId clientJobsConfigurationsId  = xoClientJobConfig.getClientJobsConfigurationsId();
    		this.configInstanceId = clientJobsConfigurationsId.getConfigInstanceId();
            final XoConfigInstance xoConfigInstance = xoClientJobConfig.getXoConfigInstance();
			this.configName = xoConfigInstance.getShortName();
    		this.jobId = clientJobsConfigurationsId.getJobId();
            final XoJob xoJob = xoClientJobConfig.getXoJob();
			this.jobName = xoJob.getName();
    		this.clientId = clientJobsConfigurationsId.getClientId();
            this.clientName = xoClientJobConfig.getXoClient().getName();
            this.lastMessage = xoClientJobConfig.getLastMsg();
            this.active = xoClientJobConfig.isActive();
            this.deleted = xoClientJobConfig.isDeleted();
            this.configJson = xoConfigInstance.getConfigJson();
            this.jobClassName = xoJob.getClassName();
    	}
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().
                append(this.configInstanceId).
                append(this.jobId).
                append(this.clientId).toHashCode();
    }


    public boolean equals(Object object) {
        if (!(object instanceof XoClientJobConfigDto)) {
            return false;
        }
        XoClientJobConfigDto other = (XoClientJobConfigDto) object;
        if ((this.configInstanceId == null && other.configInstanceId != null) || (this.configInstanceId != null && !this.configInstanceId.equals(other.configInstanceId))) {
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
