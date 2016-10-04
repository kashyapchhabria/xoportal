package com.xo.web.mgr;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.xo.web.core.XODAOException;
import com.xo.web.core.XOException;
import com.xo.web.models.dao.XoClientDAO;
import com.xo.web.models.dao.XoClientDAOImpl;
import com.xo.web.models.dao.XoClientJobConfigDao;
import com.xo.web.models.dao.XoClientJobConfigDaoImpl;
import com.xo.web.models.dao.XoConfigInstanceDAOImpl;
import com.xo.web.models.dao.XoJobDAO;
import com.xo.web.models.dao.XoJobDAOImpl;
import com.xo.web.models.system.ClientJobsConfigurationsId;
import com.xo.web.models.system.JobStatusEnum;
import com.xo.web.models.system.XoClient;
import com.xo.web.models.system.XoClientJobConfig;
import com.xo.web.models.system.XoConfigInstance;
import com.xo.web.models.system.XoJob;
import com.xo.web.util.XoAsyncTaskHandler;
import com.xo.web.util.XoUtil;
import com.xo.web.viewdtos.XoClientJobConfigDto;
import com.xo.web.work.XoWorker;
import com.xo.web.work.XoWorkerManager;

public class XoClientJobConfigLogic extends BaseLogic<XoClientJobConfig, ClientJobsConfigurationsId> {

	private final XoClientJobConfigDao xoClientJobConfigDao;
	private final XoClientDAO xoClientDAO = new XoClientDAOImpl();
	private final XoJobDAO xoJobDAO = new XoJobDAOImpl();
	private final XoConfigInstanceDAOImpl configInstanceDAO = new XoConfigInstanceDAOImpl();

	public XoClientJobConfigLogic() {
		super(new XoClientJobConfigDaoImpl());
		this.xoClientJobConfigDao = (XoClientJobConfigDao) this.entityDao;
	}

	public Set<XoClientJobConfigDto> findAllClientJobs() throws XODAOException {
		return this.convertEntitiesToDtos(super.findAll());
	}

	public Set<XoClientJobConfigDto> findAllClientJobs(Integer clientId) throws XODAOException {
		Set<XoClientJobConfigDto> allJobs = null;
		if(clientId > 0) {
			XoClient xoClient = xoClientDAO.find(clientId);
			if(xoClient != null) {
				allJobs=this.convertEntitiesToDtos(xoClient.getClientJobsConfigurations());
			}
		}
		return allJobs;
	}

	private Set<XoClientJobConfigDto> convertEntitiesToDtos(Collection<XoClientJobConfig> allXoClientJobConfigs) {
		Set<XoClientJobConfigDto> xoClientJobConfigDtos = new HashSet<XoClientJobConfigDto>();
		if(XoUtil.hasData(allXoClientJobConfigs)) {
			for(XoClientJobConfig xoClientJobConfig : allXoClientJobConfigs) {
				xoClientJobConfigDtos.add(new XoClientJobConfigDto(xoClientJobConfig));
			}
		}
		return xoClientJobConfigDtos;
	}

	public boolean validateDetails(XoClientJobConfigDto xoClientJobConfigDto) throws XOException {
		boolean isValid = false;
		if(xoClientJobConfigDto != null) {
			if(XoUtil.isNotNull(xoClientJobConfigDto.clientId, xoClientJobConfigDto.jobId, xoClientJobConfigDto.configInstanceId)) {
				isValid = true;
			}
		}
		return isValid;
	}

	public XoClientJobConfig save(XoClientJobConfigDto xoClientJobConfigDto) throws XODAOException {
		XoClientJobConfig xoClientJobConfig = null;
		if(xoClientJobConfigDto != null) {
			XoClient xoClient = this.xoClientDAO.find(xoClientJobConfigDto.clientId);
			XoJob xoJob = this.xoJobDAO.find(xoClientJobConfigDto.jobId);
			XoConfigInstance xoConfigInstance = this.configInstanceDAO.find(xoClientJobConfigDto.configInstanceId);
			xoClientJobConfig = new XoClientJobConfig(xoClient, xoJob, xoConfigInstance);
			xoClientJobConfig.setActive(true);
			xoClientJobConfig.setCreatedDate(new Date());
			xoClientJobConfig = super.save(xoClientJobConfig);
			this.addJobToScheduler(xoClientJobConfig);
		}
		return xoClientJobConfig;
	}

	public XoClientJobConfig delete(ClientJobsConfigurationsId clientJobsConfigurationsId) {
		XoClientJobConfig xoClientJobConfig = null;
		if(clientJobsConfigurationsId != null) {
			xoClientJobConfig = this.find(clientJobsConfigurationsId);
			XoWorkerManager.getXoTaskManager().cancelScheduledWork(new XoClientJobConfigDto(xoClientJobConfig));
			this.xoClientJobConfigDao.remove(xoClientJobConfig);
		}
		return xoClientJobConfig;
	}

	public boolean isConfigExists(XoClientJobConfigDto xoClientJobConfigDto) throws XOException {
		XoClientJobConfig xoclientjobconfig = this.xoClientJobConfigDao.find(new ClientJobsConfigurationsId(xoClientJobConfigDto.clientId, xoClientJobConfigDto.jobId, xoClientJobConfigDto.configInstanceId));
		return xoclientjobconfig == null;
	}

	public void runJobAsynch(ClientJobsConfigurationsId clientJobsConfigurationsId) {
		XoClientJobConfig xoClientJobConfig = this.find(clientJobsConfigurationsId);
		XoWorker xoWorker = (XoWorker) XoWorkerManager.getXoTaskManager().loadWorker(new XoClientJobConfigDto(xoClientJobConfig));
		XoAsyncTaskHandler.submitAsynchTask(xoWorker.getXoAsynchTask());
	}

	public void runJobNow(ClientJobsConfigurationsId clientJobsConfigurationsId) {
		XoClientJobConfig xoClientJobConfig = this.find(clientJobsConfigurationsId);
		XoWorker xoWorker = (XoWorker) XoWorkerManager.getXoTaskManager().loadWorker(new XoClientJobConfigDto(xoClientJobConfig));
		xoWorker.doJob();
	}

	private void addJobToScheduler(XoClientJobConfig xoClientJobConfig) {
		XoWorker xoWorker = (XoWorker) XoWorkerManager.getXoTaskManager().loadWorker(new XoClientJobConfigDto(xoClientJobConfig));
		XoWorkerManager.getXoTaskManager().scheduleWork(xoWorker);
	}

	public void updateLastStatusMsg(ClientJobsConfigurationsId clientJobsConfigurationsId, JobStatusEnum jobStatus){
		XoClientJobConfig xoClientJobConfig = this.find(clientJobsConfigurationsId);
        if(xoClientJobConfig != null){
        	xoClientJobConfig.setLastMsg(jobStatus.name());
        	xoClientJobConfig.setLastModifiedDate(new Date());
            super.update(xoClientJobConfig);
        }
    }

	public String findLastStatusMsg(ClientJobsConfigurationsId clientJobsConfigurationsId){
		String lastStatusMsg = "Unknown";
		if(clientJobsConfigurationsId != null) {
			XoClientJobConfig xoClientJobConfig = this.find(clientJobsConfigurationsId);
			if(xoClientJobConfig != null){
				lastStatusMsg = xoClientJobConfig.getLastMsg();
			}
		}
        return lastStatusMsg;
    }
}