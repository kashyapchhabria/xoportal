package com.xo.web.ext.tableau.models.dao;

import java.sql.ResultSet;

import com.xo.web.ext.tableau.models.TableauProject;
import com.xo.web.models.dao.GenericDAO;

public interface TableauProjectDao  extends GenericDAO<TableauProject, String> {
	ResultSet getFilterList();
}
