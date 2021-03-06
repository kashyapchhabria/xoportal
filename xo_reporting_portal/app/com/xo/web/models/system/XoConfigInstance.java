package com.xo.web.models.system;

import com.xo.web.audit.Auditable;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.HashSet;
import java.util.Set;

/**
 * XoConfigInstance generated by hbm2java
 */
@SuppressWarnings("serial")
@Auditable
public class XoConfigInstance extends XoConfig {

     private XoConfigTemplate xoConfigTemplate;

     private Set<XoClientJobConfig> clientJobsConfigurations = new HashSet<XoClientJobConfig>(0);

    public XoConfigInstance() {
    }

    public XoConfigInstance(String shortName, String configJson, boolean active) {
    	super(shortName, configJson, active);
    }

    public XoConfigInstance(XoConfigTemplate xoConfigTemplate,
			String shortName, String configJson) {
    	super(shortName, configJson);
    	this.xoConfigTemplate = xoConfigTemplate;
	}

	public XoConfigTemplate getXoConfigTemplate() {
        return xoConfigTemplate;
    }

    public void setXoConfigTemplate(XoConfigTemplate xoconfigTemplate) {
        this.xoConfigTemplate = xoconfigTemplate;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.configId).
                append(this.configJson).
                append(this.xoConfigTemplate).
                append(this.active).toHashCode();
    }

    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the userId fields are not set
        if (!(object instanceof XoConfigInstance)) {
            return false;
        }
        XoConfigInstance other = (XoConfigInstance) object;
        if ((this.configId == null && other.configId != null) || (this.configId != null && !this.configId.equals(other.configId))) {
            return false;
        }
        if ((this.configJson == null && other.configJson != null) || (this.configJson != null && !this.configJson.equals(other.configJson))) {
            return false;
        }
        if ((this.xoConfigTemplate == null && other.xoConfigTemplate != null) || (this.xoConfigTemplate != null && !this.xoConfigTemplate.equals(other.xoConfigTemplate))) {
            return false;
        }
        return true;
    }

	public Set<XoClientJobConfig> getClientJobsConfigurations() {
		return clientJobsConfigurations;
	}

	public void setClientJobsConfigurations(
			Set<XoClientJobConfig> clientJobsConfigurationses) {
		this.clientJobsConfigurations = clientJobsConfigurationses;
	}

}


