package com.xo.web.models.dao;

import com.xo.web.core.XODAOException;
import com.xo.web.models.system.XoConfig;

public interface XoConfigDAO<T extends XoConfig> extends GenericDAO<T, Integer>{

    T findByName(String shortName) throws XODAOException;

}
