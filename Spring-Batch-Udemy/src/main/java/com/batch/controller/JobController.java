package com.batch.controller;

import java.util.List;

/*import org.springframework.batch.core.launch.JobOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.batch.model.JobParamRequest;
import com.batch.service.JobService;*/

//@RestController
//@RequestMapping("/api/job")

// To enable this class , please uncomment the spring-boot-starter-web in the pom
public class JobController {
	
	/*@Autowired
	private JobService jobService;
	
	@Autowired
	JobOperator jobOperator;
	
	
	@GetMapping("/start/{jobName}")
	public String startJob(@PathVariable String jobName,@RequestBody List<JobParamRequest> jobParamRequest) {
		jobService.startJob(jobName,jobParamRequest);		
		return "Job Started....";
	}
	
	@GetMapping("/stop/{jobExecutionId}")
	public String stopJob(@PathVariable long jobExecutionId) {
		System.out.println("jobExecutionId : "+jobExecutionId);
		try {
			jobOperator.stop(jobExecutionId);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return "Job stopped.....";
	}*/

}
