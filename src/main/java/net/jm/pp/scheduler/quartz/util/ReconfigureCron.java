package net.jm.pp.scheduler.quartz.util;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.CronTriggerBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

@Component
public class ReconfigureCron {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReconfigureCron.class);
	
	private static final String LICITACION_CRON_EXPRESSION = "scheduler.trigger.licitacion.cron.expression";

	@Autowired 
	private SchedulerFactoryBean schedulerFactoryBean;
	
	@Autowired 
	private CronTriggerBean cronLicitacionTrigger;

	@Autowired
	private PropertiesConfiguration applicationProps;
	
	public void reconfigure() {
		if (applicationProps.getReloadingStrategy().reloadingRequired()) {
			LOGGER.info("¡Se ha modificiado el fichero de cron!.");
			String newCronExpression = (String) applicationProps.getProperty(LICITACION_CRON_EXPRESSION);
			LOGGER.info("La nueva espresion cron es: " + newCronExpression);
			
			Scheduler quartz = schedulerFactoryBean.getScheduler();
			try {
				cronLicitacionTrigger.setCronExpression(newCronExpression);
				quartz.rescheduleJob(cronLicitacionTrigger.getName(), cronLicitacionTrigger.getGroup(), cronLicitacionTrigger);
			} catch (Exception e) {
				e.printStackTrace();
				LOGGER.error("ERROR al reconfigurar el cron: ", e);
			}
		} else {
			LOGGER.info("NO se ha modificiado el fichero de cron.");
		}
	}
}
