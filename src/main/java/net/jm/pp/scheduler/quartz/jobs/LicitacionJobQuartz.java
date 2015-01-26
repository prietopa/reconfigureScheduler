package net.jm.pp.scheduler.quartz.jobs;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class LicitacionJobQuartz extends QuartzJobBean {

	private static final Logger LOGGER = LoggerFactory.getLogger(LicitacionJobQuartz.class);
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		try {
			LOGGER.info("Hilo: "+Thread.currentThread().getId()+". LicitacionJobQuartz has sido levantado por el Scheduler. Invocamos a JobLicitacion.");
			Thread.sleep(10000l);
			LOGGER.info("Hilo: "+Thread.currentThread().getId()+". Fin.");
		}catch(Exception e) {
			JobExecutionException jee = new JobExecutionException();
			jee.setErrorCode(0);
			jee.setRefireImmediately(false);
			jee.setStackTrace(e.getStackTrace());
			jee.setUnscheduleAllTriggers(false);
			jee.setUnscheduleFiringTrigger(false);
			throw jee;
		}
	}

}
