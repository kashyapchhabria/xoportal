package com.xo.web.viewdtos;

import com.xo.web.models.system.XoJob;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@SuppressWarnings("serial")
public class XoJobsDto extends BaseDto<XoJobsDto> {

	public Integer jobId;
    public String jobName;
    public String className;
    public boolean isRunning;
    public boolean isDeleted;
    public String lastMessage;


    public XoJobsDto() {
    }

    public XoJobsDto(XoJob xoJob) {
        this.isDeleted = xoJob.isDeleted();
        this.className = xoJob.getClassName();
        this.jobName = xoJob.getName();
        this.jobId = xoJob.getJobId();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().
                append(this.jobId).
                append(this.jobName).
                append(this.className).toHashCode();
    }

    @Override
    public boolean equals(Object object) {
    	if(object == null) {
    		return false;
    	}
        if (!(object instanceof XoJobsDto)) {
            return false;
        }
        XoJobsDto other = (XoJobsDto) object;
        if ((this.jobId == null && other.jobId != null) ||
                (this.jobId != null && !this.jobId.equals(other.jobId) ||
                        (this.jobName != null && !this.jobName.equalsIgnoreCase(other.jobName)))) {
            return false;
        }
        return true;
    }
}
