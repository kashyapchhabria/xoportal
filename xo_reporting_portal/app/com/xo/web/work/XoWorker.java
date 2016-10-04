package com.xo.web.work;

import play.Logger;

import com.xo.web.core.XOException;
import com.xo.web.mgr.XoClientJobConfigLogic;
import com.xo.web.mgr.XoConfigInstanceLogic;
import com.xo.web.mgr.XoJobLogic;
import com.xo.web.models.system.ClientJobsConfigurationsId;
import com.xo.web.models.system.JobStatusEnum;
import com.xo.web.persistence.XODBTransaction;
import com.xo.web.util.XoAsynchTask;
import com.xo.web.viewdtos.XoClientJobConfigDto;

public abstract class XoWorker implements Runnable {

	protected final XoJobLogic xojoblogic  = new XoJobLogic();
	protected final XoConfigInstanceLogic configInstanceLogic = new XoConfigInstanceLogic();
	protected final XoClientJobConfigLogic clientJobConfigLogic = new XoClientJobConfigLogic();
    protected final XoClientJobConfigDto xoClientJobConfigDto;

	public XoWorker(XoClientJobConfigDto clientJobsConfigDto) {
    	this.xoClientJobConfigDto = clientJobsConfigDto;
    }

	public XoClientJobConfigDto getXoClientJobConfigDto() {
		return xoClientJobConfigDto;
	}

    public boolean enabled() {
        return this.xoClientJobConfigDto.active;
    }

    public final String getConfigName() {
        return this.xoClientJobConfigDto.configName;
    }

    public final String getClientName() {
        return this.xoClientJobConfigDto.clientName;
    }
    
    public final String getJobName() {
    	return  this.xoClientJobConfigDto.jobName;
    }

    @XODBTransaction
    public void run() {
        this.doJob();
    }

    protected abstract void setConfigs() throws XOException;

    protected abstract void process() throws Exception;

    public final void doJob() {
        final ClientJobsConfigurationsId clientJobsConfigurationsId = new ClientJobsConfigurationsId(this.xoClientJobConfigDto.clientId, 
				this.xoClientJobConfigDto.jobId, this.xoClientJobConfigDto.configInstanceId);
		try {
            this.setConfigs();
			this.clientJobConfigLogic.updateLastStatusMsg(clientJobsConfigurationsId, JobStatusEnum.Initialized);
            if(this.xoClientJobConfigDto.active) {
                this.process();
            }
            this.clientJobConfigLogic.updateLastStatusMsg(clientJobsConfigurationsId, JobStatusEnum.Completed);
        } catch (Exception e) {
            Logger.error("Error while running the " + this.xoClientJobConfigDto.jobClassName, e);
            this.clientJobConfigLogic.updateLastStatusMsg(clientJobsConfigurationsId, JobStatusEnum.Failed);
        }
    }

    public XoAsynchTask getXoAsynchTask() {
        return new XoAsynchTask(this.getConfigName()) {

            public void process() throws Throwable {
                doJob();
            }
        };
    }

}