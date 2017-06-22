package com.xo.web.ext.annotatecomments.viewdtos;


import com.xo.web.ext.annotatecomments.models.AnnotateComments;
import com.xo.web.models.system.User;
import com.xo.web.viewdtos.BaseDto;

public class AnnotateCommentsDto extends BaseDto<AnnotateCommentsDto> {
	
	public Integer annotateId;
	public String user;
	public String reportName;
	public String fieldName1;
	public String fieldName2;
	public String workbookName;
	public String status;
	public String comment;

	public AnnotateCommentsDto() {
		
	}
	
	public AnnotateCommentsDto(Integer annotateId,String user,String reportName,String fieldName1,String fieldName2,String workbookName,String status,String comment)
	{
		this.annotateId=annotateId;
		this.user=user;
		this.reportName=reportName;
		this.fieldName1=fieldName1;
		this.fieldName1=fieldName2;
		this.workbookName=workbookName;
		this.status=status;
		this.comment=comment;
		
	}
	
	

}
