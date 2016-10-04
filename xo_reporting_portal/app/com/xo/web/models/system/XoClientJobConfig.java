package com.xo.web.models.system;

import org.apache.commons.lang3.builder.HashCodeBuilder;
// Generated 30 May, 2015 2:15:28 PM by Hibernate Tools 4.3.1



import com.xo.web.viewdtos.KeyValueDTO;

/**
 * XoClientJobConfig generated by hbm2java
 */
@SuppressWarnings("serial")
public class XoClientJobConfig  extends AbstractEntity {

     private ClientJobsConfigurationsId clientJobsConfigurationsId;
     private XoClient xoClient;
     private XoConfigInstance xoConfigInstance;
     private XoJob xoJob;
     private String lastMsg;

    public XoClientJobConfig() {
    }

    public XoClientJobConfig(XoClient xoClient, XoJob xoJob, XoConfigInstance xoConfigInstance) {
        this.clientJobsConfigurationsId = new ClientJobsConfigurationsId(xoClient.getClientId(),
                xoJob.getJobId(), xoConfigInstance.getConfigId());
        this.xoClient = xoClient;
        this.xoConfigInstance = xoConfigInstance;
        this.xoJob = xoJob;
    }

    public ClientJobsConfigurationsId getClientJobsConfigurationsId() {
        return this.clientJobsConfigurationsId;
    }

    public void setClientJobsConfigurationsId(ClientJobsConfigurationsId id) {
        this.clientJobsConfigurationsId = id;
    }

    public XoClient getXoClient() {
        return this.xoClient;
    }

    public void setXoClient(XoClient xoClient) {
        this.xoClient = xoClient;
    }

    public XoConfigInstance getXoConfigInstance() {
        return this.xoConfigInstance;
    }

    public void setXoConfigInstance(XoConfigInstance xoConfigInstance) {
        this.xoConfigInstance = xoConfigInstance;
    }

    public XoJob getXoJob() {
        return this.xoJob;
    }

    public void setXoJob(XoJob xoJob) {
        this.xoJob = xoJob;
    }

    public String getLastMsg() {
        return this.lastMsg;
    }

    public void setLastMsg(String lastMsg) {
        this.lastMsg = lastMsg;
    }

    @Override
    public int hashCode() {
		return new HashCodeBuilder()
				.append(xoClient)
				.append(this.xoJob)
				.append(this.xoConfigInstance)
				.toHashCode();
    }

    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the userId fields are not set
        if (!(object instanceof XoJob)) {
            return false;
        }
        XoClientJobConfig other = (XoClientJobConfig) object;
        if ((this.clientJobsConfigurationsId == null && 
        		other.clientJobsConfigurationsId != null) || 
        		(this.clientJobsConfigurationsId != null && 
        		!this.clientJobsConfigurationsId.equals(other.clientJobsConfigurationsId))) {
            return false;
        }
        return true;
    }

    public String toString() {
    	StringBuilder displayText = new StringBuilder();
    	displayText.append("Client:");
    	displayText.append(this.xoClient.getName());
    	displayText.append(";Job:");
    	displayText.append(this.xoJob.getName());
    	displayText.append(";Config:");
    	displayText.append(this.xoConfigInstance.shortName);
    	return  displayText.toString(); 
    }

    public KeyValueDTO asKeyValue() {
		return new KeyValueDTO(this.clientJobsConfigurationsId, this.toString());
	}
}
