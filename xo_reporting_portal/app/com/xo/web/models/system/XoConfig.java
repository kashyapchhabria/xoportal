package com.xo.web.models.system;

import com.xo.web.viewdtos.KeyValueDTO;

@SuppressWarnings("serial")
public class XoConfig extends AbstractEntity {

	protected Integer configId;
	protected String configJson;
	protected String shortName;

	public XoConfig(){}
	
	public XoConfig(String shortName, String configJson) {
		this.shortName = shortName;
        this.configJson = configJson;
        this.active = true;
	}

	public XoConfig(String shortName, String configJson, boolean active) {
		this(shortName, configJson);
        this.active = active;
	}

    public Integer getConfigId() {
        return this.configId;
    }

    public void setConfigId(Integer configId) {
        this.configId = configId;
    }

	public String getShortName() {
        return this.shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getConfigJson(){ 
    	return this.configJson;
    }

    public void setConfigJson(String configJson){ 
    	this.configJson = configJson;
    }

	public String toString() {
		return this.shortName;
	}

	public KeyValueDTO asKeyValue() {
		return new KeyValueDTO(this.configId, this.shortName);
	}
}