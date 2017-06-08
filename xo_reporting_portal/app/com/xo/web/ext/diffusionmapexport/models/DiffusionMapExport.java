package com.xo.web.ext.diffusionmapexport.models;

import java.util.Date;

import com.xo.web.models.system.AbstractEntity;
import com.xo.web.models.system.User;

public class DiffusionMapExport extends AbstractEntity {
	
	 private Integer exportId;
     private Date createdDate;
     private User user;
     public String deviceDate;
     public String xTile;
     public String yTile;
     public String subsegmentLabel;
     public String region;
     public String status;
     public String locationType;
     public String lifetimeBucket;
     public String spendSegment;
    
     public DiffusionMapExport() {
     }
     
     public DiffusionMapExport(Date createdDate,User user,String deviceDate,String xTile,String yTile,String subsegmentLabel,String region,String status,String locationType,String lifetimeBucket,String spendSegment){
		this.createdDate = createdDate;
		this.user = user;
		this.deviceDate=deviceDate;
		this.xTile=xTile;
		this.yTile=yTile;
		this.subsegmentLabel=subsegmentLabel;
		this.region=region;
		this.status=status;
		this.locationType=locationType;
		this.spendSegment=spendSegment;
		this.lifetimeBucket=lifetimeBucket;
    }
    
    public Integer getExportId() {
        return this.exportId;
    }
    
    public void setExportId(Integer id) {
        this.exportId = id;
    }
    
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User users) {
        this.user = users;
    }

	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	public String getDeviceDate() {
		return this.deviceDate;
	}
	public void setDeviceDate(String deviceDate) {
		this.deviceDate = deviceDate;
	}
	public String getyTile() {
		return this.yTile;
	}
	public void setyTile(String yTile) {
		this.yTile = yTile;
	}
	
	public String getxTile() {
		return this.xTile;
	}
	public void setxTile(String xTile) {
		this.xTile = xTile;
	}
	
	
	public String getSubsegmentLabel() {
		return this.subsegmentLabel;
	}
	public void setSubsegmentLabel(String subsegmentLabel) {
		this.subsegmentLabel = subsegmentLabel;
	}
	
	public String getRegion() {
		return this.region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	
	public String getStatus() {
		return this.status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getLocationType() {
		return this.locationType;
	}
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}
	
	
	public String getSpendSegment() {
		return this.spendSegment;
	}
	public void setSpendSegment(String spendSegment) {
		this.spendSegment = spendSegment;
	}
	
	public String getLifetimeBucket() {
		return this.lifetimeBucket;
	}
	public void setLifetimeBucket(String lifetimeBucket) {
		this.lifetimeBucket = lifetimeBucket;
	}
	
}
