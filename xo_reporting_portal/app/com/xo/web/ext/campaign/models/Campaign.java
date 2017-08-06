package com.xo.web.ext.campaign.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.xo.web.models.system.AbstractEntity;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Campaign extends AbstractEntity {

	public int targetId;
	public String name;
	public String description;
	public String filterJson;
	
	public Campaign() {
		
	}
	
	public Campaign(String name, String description, String filterJson) {
		this.name = name;
		this.description = description;
		this.filterJson = filterJson;
	}
	
	public void setTargetId(int targetId) {
		this.targetId = targetId;
	}
	
	public int getTargetId() {
		return this.targetId;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setFilterJson(String filterJson) {
		this.filterJson = filterJson;
	}
	
	public String getFilterJson() {
		return this.filterJson;
	}
	
}
