package com.xo.web.ext.tableau.mgr;

import com.xo.web.ext.tableau.models.TableauProject;
import com.xo.web.ext.tableau.models.dao.TableauProjectDao;
import com.xo.web.ext.tableau.models.dao.TableauProjectDaoImpl;
import com.xo.web.mgr.BaseLogic;

public class TableauProjectLogic extends BaseLogic<TableauProject, String> {

	private final TableauProjectDao tableauProjectDao;

	public TableauProjectLogic() {
		super(new TableauProjectDaoImpl());
		this.tableauProjectDao = (TableauProjectDao) this.entityDao;
	}

}
