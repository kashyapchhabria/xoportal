package com.xo.web.ext.chat.viewdtos;

import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import sun.misc.BASE64Encoder;

import com.xo.web.ext.chat.models.Chat;
import com.xo.web.ext.chat.models.ChatRoom;
import com.xo.web.models.system.User;
import com.xo.web.viewdtos.BaseDto;

public class ChatDto extends BaseDto<ChatDto>  {
	public Integer messageId;
	public Integer chatroom;
	public String message;
	public String ts;
	public String type;
	public String user;
	public byte[] imagedata;
	public String encodedImgData;
	private String imageString="data:image/png;base64,";
	SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
    

	public ChatDto() {
	}

	public ChatDto(int messageId,int chatroom,String message,Date ts,String type,String user,byte[] imagedata)
	{
		this.messageId=messageId;
		this.chatroom=chatroom;
		this.message=message;
		this.ts= df.format(ts);
		this.type=type;
		this.user=user;
		if(imagedata != null){
			byte[] imagebytes=imagedata;
			BASE64Encoder encoder = new BASE64Encoder();
	        imageString =imageString+encoder.encode(imagebytes);
			this.encodedImgData=imageString;
		}
	}
	
	public ChatDto(Chat chat) {
        this.messageId = chat.getMessageId();
		this.chatroom = chat.getChatroom().getChatroomId(); 
		this.message = chat.getMessage();
		this.ts = df.format(chat.getTs());
		this.type=chat.getType();
		this.user=chat.getUser().getFirstName();
		this.imagedata=chat.getImagedata();	
	}


	@Override
    public int hashCode() {
    	return new HashCodeBuilder().
        		append(this.messageId).
        		append(this.chatroom).
        		append(this.message).
        		append(this.type).
        		append(this.ts).
        		append(this.imagedata).
                append(this.user).toHashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ChatDto)) {
            return false;
        }
        ChatDto other = (ChatDto) object;
        if ((this.messageId == null && other.messageId != null) || 
        		(this.messageId != null && !this.messageId.equals(other.messageId) ||
        				(this.message != null && !this.message.equalsIgnoreCase(other.message)))) {
            return false;
        }
        return true;
    }
}
