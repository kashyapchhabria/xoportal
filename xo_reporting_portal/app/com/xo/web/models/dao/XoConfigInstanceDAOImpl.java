package com.xo.web.models.dao;

import com.xo.web.models.system.XoConfigInstance;
import org.hibernate.Query;

import java.util.Collection;

@SuppressWarnings("unchecked")
public class XoConfigInstanceDAOImpl extends XoConfigDAOImpl<XoConfigInstance> {

	protected static final String QUERY_SELECT_BY_CONFIG_TEMPLATE_ID = "findByXoConfigTemplateId";
	protected static final String QUERY_SELECT_BY_CONFIG_TEMPLATE_ID_AND_NAME = "findByConfigTemplateAndName";
    protected static final String PARAM_CONFIG_TEMPLATE_ID = "configTemplateId";

	public Collection<XoConfigInstance> findByXoConfigTemplateId(Integer configTemplateId) {
    	Query query = getNamedQuery(QUERY_SELECT_BY_CONFIG_TEMPLATE_ID);
		query.setParameter(PARAM_CONFIG_TEMPLATE_ID, configTemplateId);
		return query.list();
    }

	public XoConfigInstance findByConfigTemplateAndName(Integer configTemplateId, String shortName) {
		Query query = getNamedQuery(QUERY_SELECT_BY_CONFIG_TEMPLATE_ID_AND_NAME);
		query.setParameter(PARAM_CONFIG_TEMPLATE_ID, configTemplateId);
		query.setParameter(PARAM_SHORT_NAME, shortName);
		return (XoConfigInstance) query.uniqueResult();
	}

}


