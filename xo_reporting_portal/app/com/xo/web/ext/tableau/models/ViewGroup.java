package com.xo.web.ext.tableau.models;

import com.xo.web.audit.Auditable;
import com.xo.web.ext.tableau.models.TableauView;
import com.xo.web.models.system.AbstractEntity;
import com.xo.web.viewdtos.KeyValueDTO;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
@Auditable
public class ViewGroup extends AbstractEntity {

    private Integer viewGroupId;
    private String groupName;
    private Set<TableauView> tableauViews = new HashSet<TableauView>(0);

    public ViewGroup(Integer viewGroupId, Set<TableauView> tableauViews, String groupName) {
        this.viewGroupId = viewGroupId;
        this.tableauViews = tableauViews;
        this.groupName = groupName;
    }

    public Set<TableauView> getTableauViews() {
        return tableauViews;
    }

    public void setTableauViews(Set<TableauView> tableauViews) {
        this.tableauViews = tableauViews;
    }

    public ViewGroup() {
    }

    public ViewGroup(Integer viewGroupId, String groupName) {
        this.viewGroupId = viewGroupId;
        this.groupName = groupName;
    }

    public Integer getViewGroupId() {
        return viewGroupId;
    }

    public void setViewGroupId(Integer viewGroupId) {
        this.viewGroupId = viewGroupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String toString() {
    	return this.groupName;
    }

    public KeyValueDTO asKeyValue() {
		return new KeyValueDTO(this.viewGroupId, this.getDisplayName());
	}

    public String getDisplayName() {
		return this.displayName != null ? this.displayName : this.groupName;
	}
}
