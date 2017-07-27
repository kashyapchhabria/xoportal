package com.xo.web.ext.annotatecomments.models;

import java.util.Date;
import java.util.List;

import com.xo.web.core.XODAOException;
import com.xo.web.ext.comment.models.Comment;
import com.xo.web.models.dao.GenericDAOImpl;
import com.xo.web.models.system.TokenType;

import org.hibernate.Query;

public class AnnotateCommentsDaoImpl extends GenericDAOImpl<AnnotateComments, Integer> implements AnnotateCommentsDao {
	
	private static final String UPDATE_COMMENT = "updateComment";
	private static final String PARAM_FIELD_NAME_1 = "fieldName1";
	private static final String PARAM_USER = "user";
	
	private static final String GET_FIELD_COMMENT = "getFieldComment";
	
	
	public void updateComment(String fieldName1,String comment,String user) {
		Query query = getNamedQuery(UPDATE_COMMENT);
		query.setParameter("comment", comment);
		query.setParameter(PARAM_FIELD_NAME_1, fieldName1);
		query.executeUpdate();
	}
	
	public List<AnnotateComments> getFieldComment(String fieldName1) {
		Query query = getNamedQuery(GET_FIELD_COMMENT);
		query.setParameter(PARAM_FIELD_NAME_1, fieldName1);
//		query.executeUpdate();
		return (List<AnnotateComments>) query.list();
	}
}
