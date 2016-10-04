package com.xo.web.ext.tableau.models.dao;

import com.xo.web.ext.tableau.models.TableauSite;
import com.xo.web.models.dao.GenericDAO;

public interface TableauSiteDao  extends GenericDAO<TableauSite, String> {

	TableauSite findByNameAndContentUrl(String name, String contentUrl);

}
