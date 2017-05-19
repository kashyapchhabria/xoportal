package com.xo.web.ext.tableau.models.dao;

import javax.sql.RowSet;

import com.xo.web.ext.tableau.models.TableauProject;
import com.xo.web.models.dao.GenericDAO;

public interface TableauProjectDao  extends GenericDAO<TableauProject, String> {
	RowSet getFilterList();
}
