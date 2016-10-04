package com.xo.web.models.dao;

import com.xo.web.core.XODAOException;
import com.xo.web.models.system.XoJob;
import org.hibernate.Query;

public class XoJobDAOImpl extends GenericDAOImpl<XoJob, Integer> implements XoJobDAO {

    protected static final String QUERY_SELECT_BY_CLIENT_ID = "findByClientId";
    protected static final String PARAM_CLIENT_ID = "clientId";
    private static final String PARAM_CLASSNAME = "classname";
    private static final String QUERY_FIND_BY_CLASSNAME = "findByClassName";


    public Object findbyClientId(Integer clientId)  throws XODAOException {
        Query query = getNamedQuery(QUERY_SELECT_BY_CLIENT_ID);
        query.setParameter(PARAM_CLIENT_ID, clientId);
        return query.uniqueResult();
    }


    public XoJob findByClassName(String classname) {
        XoJob result = null;
        Query query = getNamedQuery(QUERY_FIND_BY_CLASSNAME);
        query.setParameter(PARAM_CLASSNAME, classname);
        result = (XoJob) query.uniqueResult();
        return result;
    }
	
}
