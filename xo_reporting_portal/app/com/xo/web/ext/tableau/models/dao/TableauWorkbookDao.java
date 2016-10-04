package com.xo.web.ext.tableau.models.dao;

import com.xo.web.ext.tableau.models.TableauWorkbook;
import com.xo.web.models.dao.GenericDAO;

public interface TableauWorkbookDao  extends GenericDAO<TableauWorkbook, String> {

	TableauWorkbook findTopWorkbookByTableauProject(String projectId);
	
}
