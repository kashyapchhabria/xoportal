package com.xo.web.ext.campaign.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import play.Logger;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown = true)
public class FilterJson {

	public String topSegment;
	public String[] subSegment;
	public String[] homeLocation;
	public String lifetime;
	public String dataArpu;
	public String vasPlan;
	public String noExported;
	public String totalBase;
	
	public FilterJson() {
		
	}
	
	public FilterJson(String topSegment, String[] subSegment, String[] homeLocation, String lifetime, String dataArpu, String vasPlan, String noExported, String totalBase) {
		this.topSegment = topSegment;
		this.subSegment = subSegment;
		this.homeLocation = homeLocation;
		this.lifetime = lifetime;
		this.dataArpu = dataArpu;
		this.vasPlan = vasPlan;
		this.noExported = noExported;
		this.totalBase = totalBase;
	}
	
	public void setTopSegment(String topSegment) {
		this.topSegment = topSegment;
	}
	
	public void setSubSegment(String[] subSegment) {
		this.subSegment = subSegment;
	}
	
	public void setHomeLocation(String[] homeLocation) {
		this.homeLocation = homeLocation;
	}
	
	public void setLifetime(String lifetime) {
		this.lifetime = lifetime;
	}
	
	public void setDataArpu(String dataArpu) {
		this.dataArpu = dataArpu;
	}
	
	public void setVasPlan(String vasPlan) {
		this.vasPlan = vasPlan;
	}
	
	public void setNoExported(String noExported) {
		this.noExported = noExported;
	}
	
	public void setTotalBase(String totalBase) {
		this.totalBase = totalBase;
	}
	
	public String getTopSegment() {
		return this.topSegment ;
	}
	
	public String[] getSubSegment() {
		return this.subSegment;
	}
	
	public String[] getHomeLocation() {
		return this.homeLocation;
	}
	
	public String getLifetime() {
		return this.lifetime;
	}
	
	public String getDataArpu() {
		return this.dataArpu;
	}
	
	public String getVasPlan() {
		return this.vasPlan;
	}
	
	public String getNoExported() {
		return this.noExported ;
	}
	
	public String getTotalBase() {
		return this.totalBase ;
	}
	
	@Override
	public String toString() {
		try {
			ObjectWriter ow = new ObjectMapper().writer();
			String json = ow.writeValueAsString(this);
			return json;
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			Logger.error("Error while converting filter json to string.", e);
		}
		return null;
	}
}