package com.batch.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.batch.model.JobParamRequest;

@Service
public class JobService {
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Qualifier("firstJob")
	@Autowired
	private Job firstJob;
	
	@Qualifier("secondJob")
	@Autowired
	private Job secondJob;
	
	@Qualifier("thirdJob")
	@Autowired
	private Job thirdJob;
	
	@Async
	public void startJob(String jobName,List<JobParamRequest> jobParamRequest) {
		
		Map<String,JobParameter> params=new HashMap<>();
		params.put("currentTime", new JobParameter(System.currentTimeMillis()));
		
		jobParamRequest.stream().forEach(jobRequest->{
			params.put(jobRequest.getParamKey(), new JobParameter(jobRequest.getParamValue()));
		});
		
		JobParameters jobParameters =new JobParameters(params);
		
		try {
				JobExecution jobExecution=null;
			if (jobName.equals("First Job")) {
				jobExecution=jobLauncher.run(firstJob, jobParameters);
			} else if (jobName.equals("Second Job")) {
				jobExecution=jobLauncher.run(secondJob, jobParameters);
			}
			if (jobName.equals("Third Job")) {
				jobExecution=jobLauncher.run(thirdJob, jobParameters);
			}
			System.out.println("jobExecution ID : "+jobExecution.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

}
