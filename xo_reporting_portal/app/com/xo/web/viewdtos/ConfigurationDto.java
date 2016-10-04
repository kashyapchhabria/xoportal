package com.xo.web.viewdtos;

import com.xo.web.models.system.XoConfig;
import com.xo.web.models.system.XoConfigInstance;
import com.xo.web.models.system.XoConfigTemplate;
import com.xo.web.util.XoUtil;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@SuppressWarnings("serial")
public class ConfigurationDto extends BaseDto<ConfigurationDto> {

	public Integer configTemplateId;
	public Integer configInstanceId;
    public String configJson;
    public String shortName;
    public boolean active;

    public ConfigurationDto() {
    }

    public ConfigurationDto(XoConfig xoConfig) {
    	if(xoConfig != null) {
    		this.active = xoConfig.isActive();
    		this.shortName = xoConfig.getShortName();
    		this.configJson = xoConfig.getConfigJson();
    		if(xoConfig instanceof XoConfigTemplate) {
    			this.configTemplateId = ((XoConfigTemplate)xoConfig).getConfigId();
    		} else if(xoConfig instanceof XoConfigInstance) {
    			XoConfigInstance configInstance = ((XoConfigInstance)xoConfig) ;
    			this.configInstanceId = configInstance.getConfigId();
    			this.configTemplateId = configInstance.getXoConfigTemplate().getConfigId();
    		}
    	}
    }

    public XoConfig asEntityObject() {
    	XoConfig xoConfig = null;
    	if(XoUtil.isNotNull(this.configInstanceId, this.configTemplateId) ) {
    		xoConfig = new XoConfigInstance(this.shortName, this.configJson, this.active);
    		xoConfig.setConfigId(this.configInstanceId);
    	} else {
    		xoConfig = new XoConfigTemplate(this.shortName, this.configJson, this.active);
    		xoConfig.setConfigId(this.configTemplateId);
    	}
        return xoConfig;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
        		.append(this.configTemplateId)
        		.append(this.configInstanceId)
        		.append(this.shortName)
        		.append(this.configJson).toHashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ConfigurationDto)) {
            return false;
        }
        ConfigurationDto other = (ConfigurationDto) object;
        if ((this.configTemplateId == null && other.configTemplateId != null) || (this.configTemplateId != null && !this.configTemplateId.equals(other.configTemplateId))) {
            return false;
        }
        if ((this.configInstanceId == null && other.configInstanceId != null) || (this.configInstanceId != null && !this.configInstanceId.equals(other.configInstanceId))) {
            return false;
        }
        if ((this.configJson == null && other.configJson != null) || (this.configJson != null && !this.configJson.equals(other.configJson))) {
            return false;
        }
        if ((this.shortName == null && other.shortName != null) || (this.shortName != null && !this.shortName.equalsIgnoreCase(other.shortName))) {
            return false;
        }
        return true;
    }
}
