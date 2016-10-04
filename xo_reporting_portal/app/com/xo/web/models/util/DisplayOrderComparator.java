package com.xo.web.models.util;

import com.xo.web.models.system.IEntity;

import java.util.Comparator;

/**
 * Created by fredrick on 24/7/15.
 */
public class DisplayOrderComparator implements Comparator<IEntity> {

    public int compare(IEntity e1, IEntity e2) {
        if(e1.getDisplayOrder() == e2.getDisplayOrder())
            return 0;
        else if(e1.getDisplayOrder() > e2.getDisplayOrder())
            return 1;
        else
            return -1;
    }
}
