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
import com.xo.web.viewdtos.ViewGroupDto;

public class ViewGroupLogic extends BaseLogic<ViewGroup, Integer>{

	private final ViewGroupDao viewGroupDao;
	private final TableauViewDao tableauViewDao;

	public ViewGroupLogic() {
		super(new ViewGroupDaoImpl());
		this.viewGroupDao = (ViewGroupDao) entityDao;
		this.tableauViewDao = new TableauViewDaoImpl();
	}

	public Set<ViewGroupDto> findAllViewGroups() {
		Collection<ViewGroup> viewGroups = this.entityDao.findAll();
		return this.convertEntitiesToDtos(viewGroups);
	}

	private Set<ViewGroupDto> convertEntitiesToDtos(Collection<ViewGroup> allViewGroups) {
		Set<ViewGroupDto> viewGroupDtos = new HashSet<ViewGroupDto>();
		if(XoUtil.hasData(allViewGroups)) {
			for(ViewGroup viewGroup : allViewGroups) {
				viewGroupDtos.add(new ViewGroupDto(viewGroup));
			}
		}
		return viewGroupDtos;
	}

	public boolean validateViewGroupDetails(ViewGroupDto viewGroupDto) throws XOException {
		boolean isValid = false;
		if(viewGroupDto != null) {
			if(XoUtil.isNotNull(viewGroupDto.groupName)) {
				ViewGroup viewGroup = this.viewGroupDao.findByName(viewGroupDto.groupName);
				if(viewGroup != null && viewGroupDto.viewGroupId != viewGroup.getViewGroupId()) {
					String message= "View Group '"+viewGroupDto.groupName+"' is already exists.";
					throw new XOException(message);
				} else {
					isValid = true;
				}
			}
		}
		return isValid;
	}

	public Integer readGroupCount(Integer viewGroupId) throws XODAOException {
		int count = 0;
		if(viewGroupId != null){
			ViewGroup viewGroup = this.viewGroupDao.find(viewGroupId);
			count = XoUtil.hasData(viewGroup.getTableauViews()) ? viewGroup.getTableauViews().size() : 0;
		}
		return count;
	}

	public ViewGroup save(ViewGroupDto viewGroupDto) throws XODAOException {
		ViewGroup viewGroup = null;
		if(viewGroupDto != null) {
			viewGroup = viewGroupDto.asEntityObject();
			viewGroup.setActive(true);
			viewGroup.setDisplayOrder(viewGroupDto.displayOrder);
			viewGroup = super.save(viewGroup);
		}
		return viewGroup;
	}

	public ViewGroupDto read(Integer id) {
		ViewGroupDto viewGroupDto = null;
		if(id > 0) {
			ViewGroup viewGroup = this.entityDao.find(id);
			if(viewGroup != null) {
				viewGroupDto = new ViewGroupDto(viewGroup);
			}
		}
		return viewGroupDto;
	}

	public void update(ViewGroupDto viewGroupDto) throws XODAOException {
		if(viewGroupDto != null) {
			ViewGroup viewGroup = this.find(viewGroupDto.viewGroupId);
			if(viewGroup != null) {
				viewGroup.setGroupName(viewGroupDto.groupName);
				viewGroup.setDisplayOrder(viewGroupDto.displayOrder);
				super.update(viewGroup);
			}
		}
	}

	public void delete(Integer viewGroupId) {
		if(viewGroupId > 0) {
			ViewGroup viewGroup = this.entityDao.find(viewGroupId);
			if(viewGroup != null) {
				Set<TableauView> userRoles = viewGroup.getTableauViews();
				if(XoUtil.hasData(userRoles)) {
					for(TableauView tableauView : userRoles) {
						this.tableauViewDao.remove(tableauView);
					}
				}
				this.viewGroupDao.remove(viewGroup);
			}
		}
	}

}
