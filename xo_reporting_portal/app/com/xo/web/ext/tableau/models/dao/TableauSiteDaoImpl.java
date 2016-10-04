package com.xo.web.ext.tableau.models.dao;

import com.xo.web.ext.tableau.models.TableauSite;
import com.xo.web.models.dao.GenericDAOImpl;
import com.xo.web.util.XoUtil;
import org.hibernate.Query;

import java.util.List;

@SuppressWarnings("unchecked")
public class TableauSiteDaoImpl  extends GenericDAOImpl<TableauSite, String> implements TableauSiteDao{

	private static final String QUERY_FIND_BY_NAME_AND_CONTENT_URL = "findByNameAndContentUrl";

	@Override
	public TableauSite findByNameAndContentUrl(String name, String contentUrl) {
		TableauSite tableauSite = null;
		if(XoUtil.isNotNull(name, contentUrl)) {

			Query query = getNamedQuery(QUERY_FIND_BY_NAME_AND_CONTENT_URL);

			query.setParameter("name", name);
			query.setParameter("contentUrl", contentUrl);
			List<TableauSite> tableauSites = query.list();
			return XoUtil.hasData(tableauSites) ? tableauSites.get(0) : null;
		}
		return tableauSite;
	}

}
