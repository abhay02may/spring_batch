package com.batch.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class FirstJobListener implements JobExecutionListener {

	@Override
	public void beforeJob(JobExecution jobExecution) {
		System.out.println("Before First Job Execution : "+jobExecution.getJobInstance().getJobName());
		System.out.println("First Job Params : "+jobExecution.getJobParameters());
		System.out.println("First Job, Execution Context : "+jobExecution.getExecutionContext());
		System.out.println("First Job Execution Started Time: "+jobExecution.getStartTime());
		jobExecution.getExecutionContext().put("jec", "Dummy Value");
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		System.out.println("After First Job Execution: "+jobExecution.getJobInstance().getJobName());
		System.out.println("After Job, Execution Context : "+jobExecution.getExecutionContext());
		System.out.println("First Job Execution End Time: "+jobExecution.getEndTime());
	}

}
