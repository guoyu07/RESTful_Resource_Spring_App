package com.tanmoy.scheduler.jobs;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.tanmoy.employee.service.SimpleService;

@DisallowConcurrentExecution
public class SimpleQuartzJobImpl extends QuartzJobBean {

	
	
	private SimpleService simpleService;


	public void setSimpleService(SimpleService simpleService) {
		this.simpleService = simpleService;
	}


	@Override
	protected void executeInternal(JobExecutionContext ctx) throws JobExecutionException {
		
		System.out.print("TANMOY BANERJEE");
		
		
		
		simpleService.createJob();
	}
	
}
