package net.jm.pp.scheduler.quartz.listener;

import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.SchedulerListener;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("licitacionSchedulerListener")
public class LicitacionSchedulerListener implements SchedulerListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(LicitacionSchedulerListener.class);
	public String name = "licitacionSchedulerListener";
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setGlobalListenerName(String name) {
        this.name = name;
    }

	public void jobScheduled(Trigger trigger) {
		String jobName = trigger.getFullJobName();
		LOGGER.info("jobScheduled: " + jobName);
	}

	public void jobUnscheduled(String triggerName, String triggerGroup) {
		LOGGER.info("jobUnscheduled, triggerName: " + triggerName +  ", triggerGroup: " + triggerGroup);
	}

	public void triggerFinalized(Trigger trigger) {
		String jobName = trigger.getFullJobName();
		LOGGER.info("triggerFinalized: " + trigger.getDescription() + ", job: " + jobName);
	}

	public void triggersPaused(String triggerName, String triggerGroup) {
		LOGGER.info("triggersPaused, triggerName: " + triggerName +  ", triggerGroup: " + triggerGroup);
	}

	public void triggersResumed(String triggerName, String triggerGroup) {
		LOGGER.info("triggersResumed, triggerName: " + triggerName +  ", triggerGroup: " + triggerGroup);
	}

	public void jobAdded(JobDetail jobDetail) {
		String jobName = jobDetail.getFullName();
		LOGGER.info("jobAdded: " + jobName);
	}

	public void jobDeleted(String jobName, String groupName) {
		LOGGER.info("jobDeleted: " + jobName + " from groupName: " + groupName);
	}

	public void jobsPaused(String jobName, String jobGroup) {
		LOGGER.info("jobsPaused: " + jobName + " from jobGroup: " + jobGroup);
	}

	public void jobsResumed(String jobName, String jobGroup) {
		LOGGER.info("jobsPaused: " + jobName + " from jobGroup: " + jobGroup);
	}

	public void schedulerError(String msg, SchedulerException cause) {
		LOGGER.info("schedulerError. msg: " + msg + ", cause: " + cause.getMessage());
	}

	public void schedulerInStandbyMode() {
		LOGGER.info("schedulerInStandbyMode.");
	}

	public void schedulerStarted() {
		LOGGER.info("schedulerStarted.");
	}

	public void schedulerShutdown() {
		LOGGER.info("schedulerShutdown.");
	}

	public void schedulerShuttingdown() {
		LOGGER.info("schedulerShuttingdown...");
	}

}
