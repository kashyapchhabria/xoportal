/**
 * 
 */
package com.xo.web.persistence.intercept;

import java.io.Serializable;
import java.net.Socket;
import java.util.Iterator;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import com.xo.web.models.system.User;
import com.xo.web.util.XoAsyncTaskHandler;
import com.xo.web.util.Serializer;
import com.xo.web.util.XoAppConfigKeys;
import com.xo.web.util.XoAsynchTask;
import com.xo.web.util.XoUtil;
import com.xo.web.viewdtos.BaseDto;
import com.xo.web.viewdtos.MessageDto;
import com.xo.web.viewdtos.MessageType;
import com.xo.web.viewdtos.UserDto;

/**
 * @author sekar
 * 
 */
@SuppressWarnings({"serial", "rawtypes"})
public class XoEntityInterceptor extends EmptyInterceptor {

	/**
	 * 
	 */
	public XoEntityInterceptor() {
	}

	public void onDelete(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
		if (entity instanceof User) {
			final UserDto userDto = new UserDto((User) entity);
			this.syncUser(MessageType.delete, userDto);
		}
		super.onDelete(entity, id, state, propertyNames, types);
	}

	// called when a Student gets updated.
	public boolean onFlushDirty(Object entity, Serializable id,
			Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {
		if (entity instanceof User) {
			final UserDto userDto = new UserDto((User) entity);
			this.syncUser(MessageType.update, userDto);
		}
		return super.onFlushDirty(entity, id, currentState, previousState, propertyNames, types);
	}

	// called on load events
	public boolean onLoad(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
		return super.onLoad(entity, id, state, propertyNames, types);
	}

	public boolean onSave(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
		if (entity instanceof User) {
			final UserDto userDto = new UserDto((User) entity);
			this.syncUser(MessageType.create, userDto);
		}
		return super.onSave(entity, id, state, propertyNames, types);
	}

	// called before commit into database
	public void preFlush(Iterator iterator) {
		super.preFlush(iterator);
	}

	// called after committed into database
	public void postFlush(Iterator iterator) {
		super.postFlush(iterator);
	}

	private final void syncUser(MessageType messageType, BaseDto<?> entityDto) {
		final MessageDto messageDto = new MessageDto("Entity Sync", messageType, entityDto);
		XoAsyncTaskHandler.submitAsynchTask(new XoAsynchTask("User Sync") {
			
			final Serializer<MessageDto> SERIALIZER = new Serializer<>(MessageDto.class);
			@Override
			public void process() throws Throwable {
				String hostname=XoUtil.getConfig(XoAppConfigKeys.XOSSO_HOSTNAME);
				Integer port=Integer.parseInt(XoUtil.getConfig(XoAppConfigKeys.XOSSO_PORT));
				Socket userSyncSocket = new Socket(hostname, port);
				userSyncSocket.getOutputStream().write(SERIALIZER.serialize(messageDto));
				userSyncSocket.close();
			}
		});
	}
}
