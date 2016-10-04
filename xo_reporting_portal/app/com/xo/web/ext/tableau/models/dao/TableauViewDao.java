package com.xo.web.ext.tableau.models.dao;

import com.xo.web.core.XODAOException;
import com.xo.web.ext.tableau.models.TableauView;
import com.xo.web.models.dao.GenericDAO;

import java.util.Collection;

public interface TableauViewDao  extends GenericDAO<TableauView, String> {

    Collection<TableauView> findByViewGroupAndDashboardStatus(Integer tableauViewId, boolean dashboardStatus) throws XODAOException;

}
