package com.xo.web.mgr;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.xo.web.models.dao.XoJobDAO;
import com.xo.web.models.dao.XoJobDAOImpl;
import com.xo.web.models.system.XoJob;
import com.xo.web.util.XoUtil;
import com.xo.web.viewdtos.XoJobsDto;

public class XoJobLogic extends BaseLogic<XoJob, Integer >{

    private final XoJobDAO xoJobDAO;

    public XoJobLogic() {
        super(new XoJobDAOImpl());
        this.xoJobDAO = (XoJobDAO) this.entityDao;
    }

    public Set<XoJobsDto> readAll() {
        Collection<XoJob> xoJobs = this.xoJobDAO.findAll();
        return convertEntitiesToDtos(xoJobs);
    }
   
    private Set<XoJobsDto> convertEntitiesToDtos(Collection<XoJob> allJobs) {
        Set<XoJobsDto> xoJobDtos = new HashSet<XoJobsDto>();
        if(XoUtil.hasData(allJobs)) {
            for(XoJob xoJob : allJobs) {
                xoJobDtos.add(new XoJobsDto(xoJob));
            }
        }
        return xoJobDtos;
    }

}
