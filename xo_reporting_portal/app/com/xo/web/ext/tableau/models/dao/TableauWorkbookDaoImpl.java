package com.xo.web.ext.tableau.models.dao;

import com.xo.web.ext.tableau.models.TableauWorkbook;
import com.xo.web.models.dao.GenericDAOImpl;
import com.xo.web.util.XoUtil;
import org.hibernate.Query;

import java.util.List;

@SuppressWarnings("unchecked")
public class TableauWorkbookDaoImpl  extends GenericDAOImpl<TableauWorkbook, String> implements TableauWorkbookDao{

	private static final String QUERY_FIND_TOP_WORKBOOK_BY_TABLEAU_PROJECT = "findTopWorkbookByTableauProject";

	public TableauWorkbook findTopWorkbookByTableauProject(String projectId) {
		Query query = getNamedQuery(QUERY_FIND_TOP_WORKBOOK_BY_TABLEAU_PROJECT);
		query.setParameter("projectId", projectId);
		query.setMaxResults(1);
		List<TableauWorkbook> workbooks = query.list();
		if(XoUtil.hasData(workbooks)) {
			return workbooks.get(0);
		} else {
			return null;
		}
	}
	
}
