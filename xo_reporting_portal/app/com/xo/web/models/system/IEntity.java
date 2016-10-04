package com.xo.web.models.system;

import java.io.Serializable;
import java.util.Date;

import com.xo.web.viewdtos.KeyValueDTO;

/**
 * Created by sekar on 10/3/14.
 */
public interface IEntity extends Serializable, Cloneable{

    Date getCreatedDate();

    void setCreatedDate(Date createdDate);

    void setLastModifiedDate(Date lastModifiedDate);

    Date getLastModifiedDate();

    boolean isDeleted();

    void setDeleted(boolean deletedStatus);

    boolean isActive();

    void setActive(boolean active);

    boolean isSystemResource();

	void setSystemResource(boolean systemUser);

	KeyValueDTO asKeyValue();

	String getDisplayName();

	void setDisplayName(String displayName);

    int getDisplayOrder();

    void setDisplayOrder(int displayOrder);
}
