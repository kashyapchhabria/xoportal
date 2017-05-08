package com.xo.web.ext.teliaexport.viewdtos;

import java.util.Date;
import java.text.SimpleDateFormat;

import com.xo.web.ext.teliaexport.models.TeliaExport;
import com.xo.web.models.system.User;
import com.xo.web.viewdtos.BaseDto;

public class TeliaExportDto extends BaseDto<TeliaExportDto> {
	
	public Integer exportId;
	public String createdDate;
	public String user;
	public String dateOfEvent;
	public Integer noOfUsers;

	SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
    
	public TeliaExportDto() {
		
	}
	
	public TeliaExportDto(Integer exportId,Date createdDate,String user,String dateOfEvent,Integer noOfUsers)
	{
		this.exportId=exportId;
		this.createdDate= df.format(createdDate);
		this.user=user;
		this.dateOfEvent=dateOfEvent;
		this.noOfUsers=noOfUsers;
	}
	
	

}
