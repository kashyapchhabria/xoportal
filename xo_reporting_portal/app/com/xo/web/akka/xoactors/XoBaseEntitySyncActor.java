package com.xo.web.akka.xoactors;

import akka.actor.UntypedActor;

import com.xo.web.persistence.XODBTransaction;
import com.xo.web.viewdtos.MessageDto;

@SuppressWarnings("incomplete-switch")
public abstract class XoBaseEntitySyncActor extends UntypedActor {

	@XODBTransaction
	public void onReceive(Object message) throws Exception {

		if(message instanceof MessageDto) {
			MessageDto messageDto = (MessageDto) message;
			MessageDto responseMessageDto = null;
			switch(messageDto.messageType) {
				case create:
					responseMessageDto = this.save(messageDto);
					break;
				case update:
					responseMessageDto = this.update(messageDto);
					break;
				case delete:
					responseMessageDto = this.delete(messageDto);
					break;
			}
			getSender().tell(responseMessageDto, getSelf());
		} else {
			this.unhandled(message);
		}
	}

	public abstract MessageDto save(MessageDto messageDto) ;

	public abstract MessageDto update(MessageDto messageDto) ;

	public abstract MessageDto delete(MessageDto messageDto) ;

}
