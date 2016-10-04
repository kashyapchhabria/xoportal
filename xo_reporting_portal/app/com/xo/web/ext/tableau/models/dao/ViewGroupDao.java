package com.xo.web.ext.tableau.models.dao;
import com.xo.web.core.XODAOException;
import com.xo.web.ext.tableau.models.ViewGroup;
import com.xo.web.models.dao.GenericDAO;

public interface ViewGroupDao extends GenericDAO<ViewGroup, Integer>{

	ViewGroup findByName(String name) throws XODAOException;
}
