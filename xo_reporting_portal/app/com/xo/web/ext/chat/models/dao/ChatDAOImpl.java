package com.xo.web.ext.chat.models.dao;

import java.util.List;

import org.hibernate.Query;

import com.xo.web.core.XODAOException;
import com.xo.web.ext.chat.models.Chat;
import com.xo.web.models.dao.GenericDAOImpl;

public class ChatDAOImpl extends GenericDAOImpl<Chat, Integer> implements ChatDAO {
    private static final String QUERY_CHATS_BY_ROOM = "findAllChatsByRoom";
    private static final String QUERY_CHATS_BY_MESSAGE = "limitChatsByRoom";

    public List<Chat> findAllChatsByRoom(Integer roomid)  throws XODAOException{
        Query query = getNamedQuery(QUERY_CHATS_BY_ROOM);
        query.setParameter("chatroomid", roomid);
        return (List<Chat>) query.list();
    }
    
    public List<Object[]> limitChatsByRoom(Integer roomid,Integer rowLimit)  throws XODAOException{
        Query query = getNamedQuery(QUERY_CHATS_BY_MESSAGE);
        query.setParameter(0, roomid);
		query.setParameter(1, rowLimit);
		List<Object[]> chatMsgs = query.list();
        return chatMsgs;
    }

}
