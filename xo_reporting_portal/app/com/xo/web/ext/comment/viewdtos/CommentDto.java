package com.xo.web.ext.comment.viewdtos;

import java.util.Date;
import java.text.SimpleDateFormat;

import com.xo.web.ext.comment.models.Comment;
import com.xo.web.viewdtos.BaseDto;

@SuppressWarnings("serial")
public class CommentDto extends BaseDto<CommentDto> {
	
	public Integer messageId;
	public String message;
	public String ts;
	public String user;
	public String sheetName;
	public String dashboardName;

	SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
    
	public CommentDto() {
	}
	
	public CommentDto(int messageId,String message,Date ts,String user,String sheetName,String dashboardName)
	{
		this.messageId=messageId;
		this.message=message;
		this.ts= df.format(ts);
		this.user=user;
		this.sheetName=sheetName;
		this.dashboardName=dashboardName;
	}
	
	public CommentDto(Comment comments) {
        this.messageId = comments.getMessageId();
		this.message = comments.getMessage();
		this.ts = df.format(comments.getTs());
		this.user=comments.getUser().getFirstName();
		this.sheetName=comments.getSheetName();
		this.dashboardName=comments.getDashboardName();
	}
	
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CommentDto)) {
            return false;
        }
        CommentDto other = (CommentDto) object;
        if ((this.messageId == null && other.messageId != null) || 
        		(this.messageId != null && !this.messageId.equals(other.messageId) ||
        				(this.message != null && !this.message.equalsIgnoreCase(other.message)))) {
            return false;
        }
        return true;
    }

}
