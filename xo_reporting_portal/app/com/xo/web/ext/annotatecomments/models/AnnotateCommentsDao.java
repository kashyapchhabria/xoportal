package com.xo.web.ext.annotatecomments.models;

import java.util.List;

import com.xo.web.core.XODAOException;
import com.xo.web.ext.comment.models.Comment;
import com.xo.web.models.dao.GenericDAO;
import com.xo.web.models.system.TokenType;

public interface AnnotateCommentsDao extends GenericDAO<AnnotateComments, Integer> {
	

	void updateComment(String fieldName1, String comment, String user);

	List<AnnotateComments> getFieldComment(String fieldName1);
}
