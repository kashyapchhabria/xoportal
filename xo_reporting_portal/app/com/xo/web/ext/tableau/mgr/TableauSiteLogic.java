package com.xo.web.ext.tableau.mgr;

import com.xo.web.ext.tableau.models.TableauSite;
import com.xo.web.ext.tableau.models.dao.TableauSiteDao;
import com.xo.web.ext.tableau.models.dao.TableauSiteDaoImpl;
import com.xo.web.mgr.BaseLogic;

public class TableauSiteLogic extends BaseLogic<TableauSite, String> {

	private final TableauSiteDao tableauSiteDao;

	public TableauSiteLogic() {
		super(new TableauSiteDaoImpl());
		this.tableauSiteDao = (TableauSiteDao) this.entityDao;
	}

}
