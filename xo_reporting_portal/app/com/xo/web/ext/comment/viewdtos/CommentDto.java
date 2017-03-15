package com.xo.web.ext.comment.viewdtos;

import java.util.Date;
import java.text.SimpleDateFormat;

import com.xo.web.ext.comment.models.Comment;
import com.xo.web.viewdtos.BaseDto;

public class CommentDto extends BaseDto<CommentDto> {
	
	public Integer messageId;
	public String message;
	public String ts;
	public String user;

	SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
    
	public CommentDto() {
	}
	
	public CommentDto(int messageId,String message,Date ts,String user)
	{
		this.messageId=messageId;
		this.message=message;
		this.ts= df.format(ts);
		this.user=user;
	}
	
	public CommentDto(Comment chat) {
        this.messageId = chat.getMessageId();
		this.message = chat.getMessage();
		this.ts = df.format(chat.getTs());
		this.user=chat.getUser().getFirstName();
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
