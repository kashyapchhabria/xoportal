package com.xo.web.models.dao;

import com.xo.web.core.XODAOException;
import com.xo.web.models.system.XoClient;

public interface XoClientDAO extends GenericDAO<XoClient, Integer> {

	XoClient findByName(String clientName) throws XODAOException;
	XoClient findBySecret(String clientSecret) throws XODAOException;
	XoClient findByNameAndSecret(String clientName, String clientSecret) throws XODAOException;
	XoClient findByNameAndActive(String clientName, boolean clientStatus) throws XODAOException;
}
