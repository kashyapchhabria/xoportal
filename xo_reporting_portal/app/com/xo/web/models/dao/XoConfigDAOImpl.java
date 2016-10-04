package com.xo.web.models.dao;

import com.xo.web.core.XODAOException;
import com.xo.web.models.system.XoConfig;
import org.hibernate.Query;

@SuppressWarnings("unchecked")
public abstract class XoConfigDAOImpl<T extends XoConfig> extends GenericDAOImpl<T, Integer> implements XoConfigDAO<T> {

    protected static final String QUERY_SELECT_BY_NAME = "findByName";
    protected static final String PARAM_SHORT_NAME = "shortName";

	public T findByName(String shortName)  throws XODAOException{
        Query query = getNamedQuery(QUERY_SELECT_BY_NAME);
        query.setParameter(PARAM_SHORT_NAME, shortName);
        return (T) query.uniqueResult();
    }
}


