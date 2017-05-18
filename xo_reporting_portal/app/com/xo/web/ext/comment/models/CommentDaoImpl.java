package com.xo.web.ext.comment.models;

import java.util.List;

import com.xo.web.core.XODAOException;
import com.xo.web.models.dao.GenericDAOImpl;

import org.hibernate.Query;

public class CommentDaoImpl extends GenericDAOImpl<Comment, Integer> implements CommentDao {

	private static final String QUERY_ALL_COMMENTS = "readAllComments";
	private static final String QUERY_SHEET_COMMENTS = "readSheetComments";
	private static final String PARAM_SHEET = "activeSheet";
	private static final String PARAM_DASHBOARD = "activeDashboard";
	
    @SuppressWarnings("unchecked")
	public List<Comment> readAllComments()  throws XODAOException{
    	
        Query query = getNamedQuery(QUERY_ALL_COMMENTS);
        return (List<Comment>) query.list();
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> readSheetComments(String activeSheet, String activeDashboard) throws XODAOException {
		
		Query query = getNamedQuery(QUERY_SHEET_COMMENTS);
		query.setParameter(PARAM_SHEET, activeSheet);
		query.setParameter(PARAM_DASHBOARD, activeDashboard);
        return (List<Comment>) query.list();
		
	}
    
}
