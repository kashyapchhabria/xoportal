package com.xo.web.models.system;

import com.xo.web.audit.Auditable;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
@Auditable
public class XoConfigTemplate extends XoConfig {

    private Set<XoConfigInstance> xoconfigInstance = new HashSet<XoConfigInstance>(0);

    public XoConfigTemplate(){}

    public XoConfigTemplate(String shortName, String configJson) {
    	super(shortName, configJson);
    }

    public XoConfigTemplate(String shortName, String configJson, boolean active) {
    	super(shortName, configJson, active);
    }

    public Set<XoConfigInstance> getXoconfigInstance() {
        return xoconfigInstance;
    }

    public void setXoconfigInstance(Set<XoConfigInstance> xoconfigInstance) {
        this.xoconfigInstance = xoconfigInstance;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.configId).
                append(this.configJson).toHashCode();
    }

    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the userId fields are not set
        if (!(object instanceof XoConfigTemplate)) {
            return false;
        }
        XoConfigTemplate other = (XoConfigTemplate) object;
        if ((this.configId == null && other.configId != null) || (this.configId != null && !this.configId.equals(other.configId))) {
            return false;
        }
        if ((this.configJson == null && other.configJson != null) || (this.configJson != null && !this.configJson.equals(other.configJson))) {
            return false;
        }
        return true;
    }


}
