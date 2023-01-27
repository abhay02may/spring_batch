package com.batch.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class FirstStepListener implements StepExecutionListener {

	@Override
	public void beforeStep(StepExecution stepExecution) {
		System.out.println("beforeStep : "+stepExecution.getStepName());
		System.out.println("beforeStep, Job Context : "+stepExecution.getJobExecution().getExecutionContext());
		System.out.println("beforeStep, Step Execution Context : "+stepExecution.getExecutionContext());
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		System.out.println("afterStep : "+stepExecution.getStepName());
		System.out.println("afterStep, Job Context : "+stepExecution.getJobExecution().getExecutionContext());
		System.out.println("afterStep, Step Execution Context : "+stepExecution.getExecutionContext());
		return null;
	}

}
