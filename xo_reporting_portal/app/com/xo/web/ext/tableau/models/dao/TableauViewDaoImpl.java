package com.xo.web.ext.tableau.models.dao;

import com.xo.web.core.XODAOException;
import com.xo.web.ext.tableau.models.TableauView;
import com.xo.web.models.dao.GenericDAOImpl;

import org.hibernate.Query;

import java.util.Collection;

@SuppressWarnings("unchecked")
public class TableauViewDaoImpl extends GenericDAOImpl<TableauView, String> implements TableauViewDao {

    private static final String QUERY_BY_VIEW_GROUP_AND_DASHBOARD_STATUS = "findByViewGroupAndDashboardStatus";
    private static final String PARAM_VIEW_GROUP_ID = "viewGroupId";
    private static final String PARAM_DASHBOARD_STATUS = "dashboardStatus";

	public Collection<TableauView> findByViewGroupAndDashboardStatus(Integer viewGroupId, boolean dashboardStatus)  throws XODAOException {
        Query query = getNamedQuery(QUERY_BY_VIEW_GROUP_AND_DASHBOARD_STATUS);
        query.setParameter(PARAM_VIEW_GROUP_ID, viewGroupId);
        query.setParameter(PARAM_DASHBOARD_STATUS, dashboardStatus);
        return query.list();
    }
}