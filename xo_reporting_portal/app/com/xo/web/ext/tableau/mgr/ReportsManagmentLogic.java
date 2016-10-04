package com.xo.web.ext.tableau.mgr;

import com.xo.web.core.XODAOException;
import com.xo.web.ext.tableau.models.TableauView;
import com.xo.web.ext.tableau.models.dao.TableauViewDao;
import com.xo.web.ext.tableau.models.dao.TableauViewDaoImpl;
import com.xo.web.models.dao.PermissionDAO;
import com.xo.web.models.dao.PermissionDAOImpl;
import com.xo.web.models.system.Permission;
import com.xo.web.util.XoUtil;
import com.xo.web.viewdtos.PermissionDto;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ReportsManagmentLogic {

//    private final TableauViewDao tableauViewDao;
//
//    public ReportsManagmentLogic() {
//        super(new TableauViewDaoImpl());
//        this.tableauViewDao = (TableauViewDao) entityDao;
//    }
//
//    public Set<TableauView> findAllTableauViews() {
//        Collection<Permission> allTableauViews = this.findAll();
//        return convertEntitiesToDtos(allTableauViews);
//    }
}