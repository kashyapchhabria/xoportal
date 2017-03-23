package com.xo.web.akka.xoactors;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xo.web.core.XOException;
import com.xo.web.util.Serializer;
import com.xo.web.util.XoAppConfigKeys;
import com.xo.web.util.XoAsyncTaskHandler;
import com.xo.web.util.XoAsynchTask;
import com.xo.web.util.XoUtil;
import com.xo.web.viewdtos.MessageDto;


@SuppressWarnings("incomplete-switch")
public abstract class XoBaseEntitySyncActor {

	protected static final Logger LOGGER = LoggerFactory.getLogger(XoBaseEntitySyncActor.class);

	protected final Serializer<MessageDto> SERIALIZER;
	private final ServerSocket clientSyncSocket;
	public XoBaseEntitySyncActor() throws IOException, XOException {
		SERIALIZER = new Serializer<>(MessageDto.class);
		clientSyncSocket = new ServerSocket(Integer.parseInt(XoUtil.getConfig(XoAppConfigKeys.XOPORTAL_PORT)));	
	}

	public final void startSyncProcess() throws IOException, XOException {
		XoAsyncTaskHandler.submitAsynchTask(new XoAsynchTask("Client Sync") {
			@Override
			public void process() throws Throwable {
				while (true) {
		            Socket socket = clientSyncSocket.accept();
		            try {
		                final MessageDto messageDto = SERIALIZER.deserialize(socket.getInputStream());
		                XoAsyncTaskHandler.submitAsynchTask(new XoAsynchTask("Client Sync") {
		        			@Override
		        			public void process() throws Throwable {
		        				onReceive(messageDto);
		        			}
		        		});
		            } finally {
		                socket.close();
		            }
		        }				
			}
		});
	}
	
	public final void onReceive(Object message) throws Exception {
		try{
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
		} catch(Exception e) {
			LOGGER.error("Error while synching the client details.", e);
		}
	}
	
	public void closeClientSyncSockets() throws IOException {
		try {
			clientSyncSocket.close();
		} finally {
			clientSyncSocket.close();
		}
	}
	public abstract MessageDto save(MessageDto messageDto) ;
	
	public abstract MessageDto update(MessageDto messageDto) ;
	
	public abstract MessageDto delete(MessageDto messageDto) ;

}
