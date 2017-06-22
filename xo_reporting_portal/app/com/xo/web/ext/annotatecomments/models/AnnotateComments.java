package com.xo.web.ext.annotatecomments.models;


import com.xo.web.models.system.AbstractEntity;
import com.xo.web.models.system.User;

public class AnnotateComments extends AbstractEntity {
	
	public Integer annotateId;
	public User user;
	public String reportName;
	public String fieldName1;
	public String fieldName2;
	public String workbookName;
	public String status;
	public String comment;
    
     public AnnotateComments() {
     }
     
     public AnnotateComments(Integer annotateId,User user,String reportName,String fieldName1,String fieldName2,String workbookName,String status,String comment){
    	this.annotateId=annotateId;
 		this.user=user;
 		this.reportName=reportName;
 		this.fieldName1=fieldName1;
 		this.fieldName2=fieldName2;
 		this.workbookName=workbookName;
 		this.status=status;
 		this.comment=comment;
    }
    
    public Integer getAnnotateId() {
        return this.annotateId;
    }
    
    public void setAnnotateId(Integer id) {
        this.annotateId = id;
    }
    
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User users) {
        this.user = users;
    }
	
	public String getReportName() {
		return this.reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	
	public String getFieldName1() {
		return this.fieldName1;
	}
	public void setFieldName1(String fieldName1) {
		this.fieldName1 = fieldName1;
	}
	
	public String getFieldName2() {
		return this.fieldName2;
	}
	public void setFieldName2(String fieldName2) {
		this.fieldName2 = fieldName2;
	}
	
	public String getWorkbookName() {
		return this.workbookName;
	}
	public void setWorkbookName(String workbookName) {
		this.workbookName = workbookName;
	}
	
	public String getStatus() {
		return this.status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getComment() {
		return this.comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

}
