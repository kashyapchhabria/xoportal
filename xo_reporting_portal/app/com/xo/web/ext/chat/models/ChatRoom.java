package com.xo.web.ext.chat.models;

import java.util.HashSet;
import java.util.Set;

import com.xo.web.audit.Auditable;
import com.xo.web.models.system.AbstractEntity;

@SuppressWarnings("serial")
@Auditable
public class ChatRoom  extends AbstractEntity{
   
	private Integer chatroomId;
    private String name;
    private Set<Chat> chat=new HashSet<>();
    

    public ChatRoom() {
    }

    public ChatRoom(Integer id,String name) {
    	this.chatroomId=id;
    	this.name=name;
    }
    
    public Integer getChatroomId() {
        return this.chatroomId;
    }
    
    public void setChatroomId(Integer id) {
        this.chatroomId = id;
    }
    
    
    public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Set<Chat> getChat(){
		return this.chat;
	}

	public void setChat(Set<Chat> chat){
		this.chat=chat;
	}
}
