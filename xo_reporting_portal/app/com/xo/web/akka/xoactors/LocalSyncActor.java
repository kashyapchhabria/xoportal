package com.xo.web.akka.xoactors;

import akka.actor.ActorSelection;
import akka.actor.UntypedActor;

import com.xo.web.util.XoAppConfigKeys;
import com.xo.web.util.XoUtil;
import com.xo.web.viewdtos.MessageDto;


public class LocalSyncActor extends UntypedActor{

	private static final String ACTOR_USERSYNC = XoUtil.getConfig(XoAppConfigKeys.XOACTORS_USERSYNC);
	private ActorSelection remoteActor;

    public void preStart()
    {
        /* Get reference to Master Node*/
    	remoteActor = getContext().actorSelection(ACTOR_USERSYNC);
    }
	
	@Override
	public void onReceive(Object message) throws Exception {

		if(message instanceof MessageDto) {
			MessageDto messageDto = (MessageDto) message;
			remoteActor.tell(messageDto, getSender());
		} else {
			this.unhandled(message);
		}
	}

}
