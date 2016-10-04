package com.xo.web.ext.chat.viewdtos;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.xo.web.ext.chat.models.ChatRoom;
import com.xo.web.models.system.Role;
import com.xo.web.viewdtos.BaseDto;

@SuppressWarnings("serial")
public class ChatRoomDTO extends BaseDto<ChatRoomDTO>{
	public Integer chatroomid;
	public String name;
	
	public ChatRoomDTO(){
		
	}

	public ChatRoomDTO(ChatRoom chatRoom) {
        this.chatroomid =chatRoom.getChatroomId();
		this.name = chatRoom.getName();
	}
	
	public ChatRoomDTO(Integer id,String name){
		this.chatroomid=id;
		this.name=name;
	}
	
	public ChatRoom asEntityObject() {
		return new ChatRoom(this.chatroomid, this.name);
	}
	
	@Override
    public int hashCode() {
		return new HashCodeBuilder().append(this.chatroomid).
				append(this.name).toHashCode();
    }
}
