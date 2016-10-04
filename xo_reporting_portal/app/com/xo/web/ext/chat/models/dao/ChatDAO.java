package com.xo.web.ext.chat.models.dao;
import java.util.Collection;
import java.util.List;

import com.xo.web.core.XODAOException;
import com.xo.web.ext.chat.models.Chat;
import com.xo.web.models.dao.GenericDAO;

public interface ChatDAO  extends GenericDAO<Chat, Integer>{
    List<Chat> findAllChatsByRoom(Integer roomid) throws XODAOException;
    List<Object[]> limitChatsByRoom(Integer roomid,Integer rowLimit) throws XODAOException;

}
