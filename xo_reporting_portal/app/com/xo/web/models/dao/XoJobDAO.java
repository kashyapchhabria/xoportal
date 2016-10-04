package com.xo.web.models.dao;

import com.xo.web.core.XODAOException;
import com.xo.web.models.system.XoJob;

import java.util.List;

public interface XoJobDAO extends GenericDAO<XoJob, Integer> {

    Object findbyClientId(Integer clientId) throws XODAOException;
    XoJob findByClassName(String classname);


}
