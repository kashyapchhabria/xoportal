package com.xo.web.ext.diffusionmapexport.viewdtos;

import java.util.Date;
import java.text.SimpleDateFormat;

import com.xo.web.ext.diffusionmapexport.models.DiffusionMapExport;
import com.xo.web.models.system.User;
import com.xo.web.viewdtos.BaseDto;

public class DiffusionMapExportDto extends BaseDto<DiffusionMapExportDto> {
	
	public Integer exportId;
	public String createdDate;
	public String user;
	public String deviceDate;
	public String xTile;
	public String yTile;
	public String subsegmentLabel;
	public String region;
	public String status;
	public String locationType;
	public String lifetimeBucket;
	public String spendSegment;

	SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
    
	public DiffusionMapExportDto() {
		
	}
	
	public DiffusionMapExportDto(Integer exportId,Date createdDate,String user,String deviceDate,String xTile,String yTile,String subsegmentLabel,String region,String status,String locationType,String lifetimeBucket,String spendSegment)
	{
		this.exportId=exportId;
		this.createdDate= df.format(createdDate);
		this.user=user;
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
}
