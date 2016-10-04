package com.xo.web.viewdtos;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.xo.web.ext.tableau.models.TableauView;
import com.xo.web.ext.tableau.models.ViewGroup;

@SuppressWarnings("serial")
public class TableauViewDto extends BaseDto<TableauViewDto> {

	public String tableauViewId;
    public String name;
    public String displayName;
    public boolean active;
    public boolean dashboard;
    public Integer displayOrder;
    public Integer viewGroupId;
    public String groupName;

    public TableauViewDto() {
    }

    public TableauViewDto(TableauView tableauView) {
    	if(tableauView != null) {
    		this.tableauViewId = tableauView.getTableauViewId();
    		this.name = tableauView.getName();
    		this.displayName = tableauView.getDisplayName();
    		this.active = tableauView.isActive();
    		this.dashboard = tableauView.isDashboard();
    		this.displayOrder = tableauView.getDisplayOrder();

    		ViewGroup viewGroup = tableauView.getViewGroup();
    		if(viewGroup != null) {
    			this.viewGroupId = viewGroup.getViewGroupId();
    			this.groupName = viewGroup.getDisplayName();
    		}
    	}
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().
        		append(this.tableauViewId).
        		append(this.name).
        		append(this.displayName).
        		append(this.active).
        		append(this.dashboard).
                append(this.viewGroupId).
                append(this.groupName).toHashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TableauViewDto)) {
            return false;
        }
        TableauViewDto other = (TableauViewDto) object;
        if ((this.tableauViewId == null && other.tableauViewId != null) ||
                (this.tableauViewId != null && !this.tableauViewId.equals(other.tableauViewId))) {
            return false;
        }
        if ((this.viewGroupId == null && other.viewGroupId != null) ||
                (this.viewGroupId != null && !this.viewGroupId.equals(other.viewGroupId))) {
            return false;
        }
        return true;
    }
}
