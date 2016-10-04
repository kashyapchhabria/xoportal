package com.xo.web.ext.tableau.mgr;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.xo.web.core.XODAOException;
import com.xo.web.core.XOException;
import com.xo.web.ext.tableau.models.TableauView;
import com.xo.web.ext.tableau.models.ViewGroup;
import com.xo.web.ext.tableau.models.dao.TableauViewDao;
import com.xo.web.ext.tableau.models.dao.TableauViewDaoImpl;
import com.xo.web.ext.tableau.models.dao.ViewGroupDao;
import com.xo.web.ext.tableau.models.dao.ViewGroupDaoImpl;
import com.xo.web.mgr.BaseLogic;
import com.xo.web.util.XoUtil;
import com.xo.web.viewdtos.TableauViewDto;

public class TableauViewLogic extends BaseLogic<TableauView, String> {

    private final ViewGroupDao viewGroupDao;
	private final TableauViewDao tableauViewDao;

	public TableauViewLogic() {
		super(new TableauViewDaoImpl());
		this.viewGroupDao = new ViewGroupDaoImpl();
		this.tableauViewDao =  new TableauViewDaoImpl();
	}

	public Set<TableauViewDto> findAllTableauViews() {
		Collection<TableauView> allRoles = this.findAll();
		return this.convertEntitiesToDtos(allRoles);
	}

	private Set<TableauViewDto> convertEntitiesToDtos(Collection<TableauView> tableauViews) {
		Set<TableauViewDto> tableauViewDtos = new HashSet<TableauViewDto>();
		if(XoUtil.hasData(tableauViews)) {
			for(TableauView tableauView : tableauViews) {
				tableauViewDtos.add(new TableauViewDto(tableauView));
			}
		}
		return tableauViewDtos;
	}

	public boolean validateTableauViewDetails(TableauViewDto tableauViewDto) throws XOException {
		boolean isValid = false;
		if(XoUtil.isNotNull(tableauViewDto, tableauViewDto.tableauViewId, tableauViewDto.viewGroupId)) {
			ViewGroup viewGroup = this.viewGroupDao.find(tableauViewDto.viewGroupId);
			TableauView tableauView = this.entityDao.find(tableauViewDto.tableauViewId);
			if(!XoUtil.isNotNull(tableauView, viewGroup)) {
				String message= "Tableau View / View group is not exists.";
				throw new XOException(message);
			} else {
				isValid = true;
			}
		} else {
			String message= "Tableau View / View group is not exists.";
			throw new XOException(message);
		}
		return isValid;
	}

	public TableauViewDto read(String tableauViewId) {
		TableauViewDto tableauViewDto = null;
		if(XoUtil.isNotNull(tableauViewId)) {
			TableauView tableauView = this.entityDao.find(tableauViewId);
			if(tableauView != null) {
				tableauViewDto = new TableauViewDto(tableauView);
			}
		}
		return tableauViewDto;
	}

	public void update(TableauViewDto tableauViewDto) throws XODAOException {
		if(tableauViewDto != null) {
			TableauView tableauView = this.entityDao.find(tableauViewDto.tableauViewId);
			ViewGroup viewGroup = this.viewGroupDao.find(tableauViewDto.viewGroupId);
			if(XoUtil.isNotNull(tableauView, viewGroup)) {
				tableauView.setDashboard(tableauViewDto.dashboard);
				tableauView.setDisplayName(tableauViewDto.displayName);
				tableauView.setDisplayOrder(tableauViewDto.displayOrder);
				tableauView.setViewGroup(viewGroup);
				super.update(tableauView);
			}
		}
	}

	@Override
	public void toggleActiveStatus(String s) {
		super.toggleActiveStatus(s);
	}

	public void toggleDashboard(String tableauViewId ) {
		if(tableauViewId != null) {
			TableauView tableauView = this.entityDao.find(tableauViewId);
			if(tableauView != null) {
				tableauView.setDashboard(!tableauView.isDashboard());
				this.entityDao.merge(tableauView);
			}
		}
	}

	public boolean isDashboardExist(Integer viewGroupId, boolean dashboardStatus) throws XODAOException {
		boolean status = false;
		if(viewGroupId != null){
			Collection<TableauView> tableauViews = this.tableauViewDao.findByViewGroupAndDashboardStatus(viewGroupId,dashboardStatus);
			status = XoUtil.hasData(tableauViews);
		}
		return status;
	}
}
