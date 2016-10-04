package com.xo.web.models.dao;

import org.hibernate.Query;

import com.xo.web.core.XODAOException;
import com.xo.web.models.system.XoClient;

public class XoClientDAOImpl extends GenericDAOImpl<XoClient, Integer> implements XoClientDAO {

	public static final String QUERY_FIND_BY_NAME = "findByName";
	public static final String QUERY_FIND_BY_SECRET = "findBySecret";
	public static final String QUERY_FIND_BY_NAME_AND_SECRET = "findByNameAndSecret";
	public static final String QUERY_FIND_BY_NAME_AND_ACTIVE = "findByNameAndActive";
	public static final String PARAM_CLIENT_NAME = "clientName";
	public static final String PARAM_CLIENT_STATUS = "clientStatus";
	public static final String PARAM_CLIENT_SECRET = "clientSecret";

	public XoClient findByNameAndActive(String clientName, boolean clientStatus) throws XODAOException {
		XoClient result = null;
		Query query = getNamedQuery(QUERY_FIND_BY_NAME_AND_ACTIVE);
		query.setParameter(PARAM_CLIENT_NAME, clientName);
		query.setParameter(PARAM_CLIENT_STATUS, clientStatus);
		result = (XoClient) query.uniqueResult();
		return result;
	}

	public XoClient findByName(String clientName) throws XODAOException {
		XoClient result = null;
		Query query = getNamedQuery(QUERY_FIND_BY_NAME);
		query.setParameter(PARAM_CLIENT_NAME, clientName);
		result = (XoClient) query.uniqueResult();
		return result;
	}

	public XoClient findByNameAndSecret(String clientName, String clientSecret) throws XODAOException {
		XoClient result = null;
		Query query = getNamedQuery(QUERY_FIND_BY_NAME_AND_SECRET);
		query.setParameter(PARAM_CLIENT_NAME, clientName);
		query.setParameter(PARAM_CLIENT_SECRET, clientSecret);
		result = (XoClient) query.uniqueResult();
		return result;
	}

	public XoClient findBySecret(String clientSecret) throws XODAOException {
		XoClient result = null;
		Query query = getNamedQuery(QUERY_FIND_BY_SECRET);
		query.setParameter(PARAM_CLIENT_SECRET, clientSecret);
		result = (XoClient) query.uniqueResult();
		return result;
	}
}
