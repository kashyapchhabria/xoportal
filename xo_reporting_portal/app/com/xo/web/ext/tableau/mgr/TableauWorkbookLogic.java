package com.xo.web.ext.tableau.mgr;

import com.xo.web.ext.tableau.models.TableauWorkbook;
import com.xo.web.ext.tableau.models.dao.TableauWorkbookDao;
import com.xo.web.ext.tableau.models.dao.TableauWorkbookDaoImpl;
import com.xo.web.mgr.BaseLogic;

public class TableauWorkbookLogic extends BaseLogic<TableauWorkbook, String> {

	private final TableauWorkbookDao tableauWorkbookDao;

	public TableauWorkbookLogic() {
		super(new TableauWorkbookDaoImpl());
		this.tableauWorkbookDao = (TableauWorkbookDao) this.entityDao;
	}

}
