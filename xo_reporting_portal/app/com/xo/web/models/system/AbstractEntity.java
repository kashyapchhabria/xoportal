package com.xo.web.models.system;

import java.util.Date;

import com.xo.web.viewdtos.KeyValueDTO;

/**
 * Created by sekar on 10/3/14.
 */
@SuppressWarnings("serial")
public class AbstractEntity implements IEntity {

    protected Date createdDate;

    protected Date lastModifiedDate;

    protected boolean deleted;

    protected boolean active;

    protected boolean systemResource;

    //UI related fields
    protected String displayName;
    protected int displayOrder;

    @Override
    public Date getCreatedDate() {
        return this.createdDate;
    }

    @Override
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Override
    public Date getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public boolean isDeleted(){
        return this.deleted;
    }

    public void setDeleted(boolean deletedStatus){
        this.deleted = deletedStatus;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isSystemResource() {
        return systemResource;
    }

    public void setSystemResource(boolean systemUser) {
        this.systemResource = systemUser;
    }

    public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    @Override
    public KeyValueDTO asKeyValue() {
        return null;
    }

}
