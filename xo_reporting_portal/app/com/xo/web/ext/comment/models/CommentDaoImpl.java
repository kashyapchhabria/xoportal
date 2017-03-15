package com.xo.web.ext.comment.models;

import java.util.List;

import com.xo.web.core.XODAOException;
import com.xo.web.models.dao.GenericDAOImpl;

import org.hibernate.Query;

public class CommentDaoImpl extends GenericDAOImpl<Comment, Integer> implements CommentDao {

	private static final String QUERY_CHATS_BY_ROOM = "readAllComments";
    
    public List<Comment> readAllComments()  throws XODAOException{
    	
        Query query = getNamedQuery(QUERY_CHATS_BY_ROOM);
        return (List<Comment>) query.list();
    }
    
}
