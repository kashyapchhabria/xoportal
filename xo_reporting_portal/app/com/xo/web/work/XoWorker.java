package com.xo.web.work;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.JsonNode;
import com.xo.web.core.XOException;
import com.xo.web.mgr.XoClientJobConfigLogic;
import com.xo.web.mgr.XoConfigInstanceLogic;
import com.xo.web.mgr.XoJobLogic;
import com.xo.web.models.system.ClientJobsConfigurationsId;
import com.xo.web.models.system.JobStatusEnum;
import com.xo.web.persistence.XODBTransaction;
import com.xo.web.util.XoAsynchTask;
import com.xo.web.util.XoUtil;
import com.xo.web.viewdtos.XoClientJobConfigDto;

import play.Logger;
import play.libs.Json;

public abstract class XoWorker implements Runnable {
	protected static final String FREQUENCY = "frequency";
	protected static final String INITIALDELAY = "initialdelay";
	protected static final String IS_SCHEDULED = "isScheduled";

	protected final XoJobLogic xojoblogic  = new XoJobLogic();
	protected final XoConfigInstanceLogic configInstanceLogic = new XoConfigInstanceLogic();
	protected final XoClientJobConfigLogic clientJobConfigLogic = new XoClientJobConfigLogic();
    protected final XoClientJobConfigDto xoClientJobConfigDto;

    protected JsonNode configJsonNode;
    
    protected long initialDelay = 0 ;
	protected long frequency = 1;
	protected TimeUnit initialTimeUnit;
	protected TimeUnit frequencyTimeInit;
	protected boolean isScheduled = false;
	
	private static final String TIME_VALUE_FORMAT = "\\s*(\\d+)\\s*([h|H|m|M|s|S|d|D|n|N]?)";
	private static final Pattern TIME_PATTERN = Pattern.compile(TIME_VALUE_FORMAT);
	
	public XoWorker(XoClientJobConfigDto clientJobsConfigDto) {
    	this.xoClientJobConfigDto = clientJobsConfigDto;
    	try {
			this.setConfigs();
		} catch (XOException e) {
			e.printStackTrace();
		}
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

    protected void setConfigs() throws XOException {
		try {
			if(XoUtil.isNotNull(this.xoClientJobConfigDto))
			{
				this.configJsonNode = Json.parse(this.xoClientJobConfigDto.configJson);

			// Setting initial delay time
				String initialDelayStr = this.configJsonNode.findPath(INITIALDELAY).asText();
				Object[] timeAndUnitValues = this.getTimeAndUnit(initialDelayStr);
				this.initialDelay = (int) timeAndUnitValues[0];
				this.initialTimeUnit = (TimeUnit) timeAndUnitValues[1];
	
				// Setting the frequency value
				String frequencyDelayStr = this.configJsonNode.findPath(FREQUENCY).asText();
				timeAndUnitValues = this.getTimeAndUnit(frequencyDelayStr);
				this.frequency = (int) timeAndUnitValues[0];
				this.frequencyTimeInit = (TimeUnit) timeAndUnitValues[1];
				
				// Setting the scheduler
				this.isScheduled = this.configJsonNode.findPath(IS_SCHEDULED).asBoolean();
			}
		} catch (Exception e) {
			Logger.error("Scheduler config load Failed.");
			throw new XOException(e);
		}
	}

	protected Object[] getTimeAndUnit(String timeValue) {
		Object[] timeAndUnitValues = null;
		Matcher timeValueMatcher = TIME_PATTERN.matcher(timeValue);
		if(timeValueMatcher.find() && timeValueMatcher.groupCount() ==2) {
			timeAndUnitValues = new Object[2];
			timeAndUnitValues[0] = Integer.parseInt(timeValueMatcher.group(1));
			timeAndUnitValues[1] = this.getTimeUnit(timeValueMatcher.group(2));
		} else {
			timeAndUnitValues = new Object[2];
			timeAndUnitValues[0] = new Integer(0);
			timeAndUnitValues[1] = TimeUnit.HOURS;
		}
		return timeAndUnitValues;
	}

	protected TimeUnit getTimeUnit(final String timeUnitStr) {
		if(timeUnitStr != null) {
			switch(timeUnitStr) {
			case "n":
			case "N":
				return TimeUnit.NANOSECONDS;
			case "m":
				return TimeUnit.MILLISECONDS;
			case "s":
			case "S":
				return TimeUnit.SECONDS;
			case "M":
				return TimeUnit.MINUTES;
			case "d":
				return TimeUnit.DAYS;
			case "h":
			default:
				return TimeUnit.HOURS;
			}
		}
		return TimeUnit.HOURS;
	}
}