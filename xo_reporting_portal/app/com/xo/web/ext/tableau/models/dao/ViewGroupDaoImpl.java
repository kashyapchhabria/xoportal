package com.xo.web.ext.tableau.models.dao;

import org.hibernate.Query;

import com.xo.web.core.XODAOException;
import com.xo.web.ext.tableau.models.ViewGroup;
import com.xo.web.models.dao.GenericDAOImpl;

public class ViewGroupDaoImpl extends GenericDAOImpl<ViewGroup, Integer> implements ViewGroupDao{

	private static final String QUERY_SELECT_BY_NAME = "findByName";
	private static final String PARAM_GROUP_NAME = "groupName";

	public ViewGroup findByName(String name)  throws XODAOException{
		Query query = getNamedQuery(QUERY_SELECT_BY_NAME);
		query.setParameter(PARAM_GROUP_NAME, name);
		return (ViewGroup) query.uniqueResult();
	}
}
