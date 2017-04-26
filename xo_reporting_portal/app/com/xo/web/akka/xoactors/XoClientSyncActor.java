package com.xo.web.akka.xoactors;

import com.xo.web.mgr.XoClientLogic;
import com.xo.web.viewdtos.MessageDto;
import com.xo.web.viewdtos.MessageType;
import com.xo.web.viewdtos.XoClientDto;

import java.io.IOException;
import com.xo.web.core.XOException;

public class XoClientSyncActor extends XoBaseEntitySyncActor {

	private final XoClientLogic XO_CLIENT_LOGIC = new XoClientLogic();

	public XoClientSyncActor() throws IOException, XOException {
	}

	public MessageDto save(MessageDto messageDto) {
		XoClientDto xoClientDto = (XoClientDto) messageDto.resultobject;
		try {
			XO_CLIENT_LOGIC.save(xoClientDto);
			messageDto.messageType = MessageType.success;
		} catch(Exception e) {
			LOGGER.error("Error while saving the client details.", e);
			messageDto.message = e.getMessage();
			messageDto.messageType = MessageType.error;
		}
		return messageDto;
	}

	public MessageDto update(MessageDto messageDto) {
		XoClientDto xoClientDto = (XoClientDto) messageDto.resultobject;
		try {
			XO_CLIENT_LOGIC.update(xoClientDto);
			messageDto.messageType = MessageType.success;
		} catch(Exception e) {
			LOGGER.error("Error while updating the client details.", e);
			messageDto.message = e.getMessage();
			messageDto.messageType = MessageType.error;
		}
		return messageDto;
	}

	public MessageDto delete(MessageDto messageDto) {
		XoClientDto xoClientDto = (XoClientDto) messageDto.resultobject;
		try {
			XO_CLIENT_LOGIC.delete(xoClientDto);
			messageDto.messageType = MessageType.success;
		} catch(Exception e) {
			LOGGER.error("Error while deleting the client details.", e);
			messageDto.message = e.getMessage();
			messageDto.messageType = MessageType.error;
		}
		return messageDto;
	}
}
