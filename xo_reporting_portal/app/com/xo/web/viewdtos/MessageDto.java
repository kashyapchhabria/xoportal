package com.xo.web.viewdtos;


@SuppressWarnings("serial")
public class MessageDto extends BaseDto<MessageDto> {

	public String message;
	public String description;
	public MessageType messageType;
	public BaseDto<? extends BaseDto<?>> resultobject;

	public MessageDto() {
		
	}

	public MessageDto(String message) {
		this.message = message;
	}

	public MessageDto(String message, MessageType messageType) {
		this.message = message;
		this.messageType = messageType;
	}

	public MessageDto(String message, MessageType messageType, BaseDto<? extends BaseDto<?>> resultobject) {
		this.message = message;
		this.messageType = messageType;
		this.resultobject = resultobject;
	}
}
