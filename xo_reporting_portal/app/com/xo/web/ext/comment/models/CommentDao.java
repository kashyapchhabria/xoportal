package com.xo.web.ext.comment.models;

import java.util.List;

import com.xo.web.core.XODAOException;
import com.xo.web.models.dao.GenericDAO;

public interface CommentDao extends GenericDAO<Comment, Integer> {

	List<Comment> readAllComments() throws XODAOException;
	List<Comment> readSheetComments(String activeSheet, String activeDashboard) throws XODAOException;
}
