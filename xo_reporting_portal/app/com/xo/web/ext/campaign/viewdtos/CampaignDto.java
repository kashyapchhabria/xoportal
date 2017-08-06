package com.xo.web.ext.campaign.viewdtos;

import java.io.IOException;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xo.web.ext.campaign.models.Campaign;
import com.xo.web.ext.campaign.models.FilterJson;
import com.xo.web.viewdtos.BaseDto;

import play.libs.Json;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CampaignDto extends BaseDto<CampaignDto> {

	private ObjectMapper mapper;
	
	public Integer targetId;
	public String name;
	public String description;
	public FilterJson filterJson;
	public Date createdDate;
	
	public CampaignDto() {
		
	}
	
	public CampaignDto(Campaign campaign) throws JsonParseException, JsonMappingException, IOException {
		mapper = new ObjectMapper();
		this.targetId = campaign.getTargetId();
		this.name = campaign.getName();
		this.description = campaign.getDescription();
		this.filterJson = mapper.readValue(campaign.getFilterJson(), FilterJson.class);
		this.createdDate = campaign.getCreatedDate();
	}
	
	public Campaign asEntityObject() {
		return new Campaign(this.name, this.description, this.filterJson.toString());
	}
}
