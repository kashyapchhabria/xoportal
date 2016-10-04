package com.xo.web.viewdtos;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.xo.web.ext.tableau.models.ViewGroup;

@SuppressWarnings("serial")
public class ViewGroupDto extends BaseDto<ViewGroupDto> {

    public Integer viewGroupId;
    public String groupName;
    public String displayName;
    public boolean active;
    public Integer displayOrder;

    public ViewGroupDto() {
    }

    public ViewGroupDto(ViewGroup group) {
        this.groupName = group.getGroupName();
        this.displayName = group.getDisplayName();
        this.viewGroupId = group.getViewGroupId();
        this.displayOrder = group.getDisplayOrder();
        this.active = group.isActive();
    }

    public ViewGroupDto(Integer viewGroupId, String groupName) {
        this.viewGroupId = viewGroupId;
        this.groupName = groupName;
    }

    public ViewGroup asEntityObject() {
    	return new ViewGroup(this.viewGroupId, this.groupName);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().
                append(this.viewGroupId).
                append(this.groupName).toHashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ViewGroupDto)) {
            return false;
        }
        ViewGroupDto other = (ViewGroupDto) object;
        if ((this.viewGroupId == null && other.viewGroupId != null) || 
        		(this.viewGroupId != null && !this.viewGroupId.equals(other.viewGroupId) ||
        				(this.groupName != null && !this.groupName.equalsIgnoreCase(other.groupName)))) {
            return false;
        }
        return true;
    }
}
