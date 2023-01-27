package com.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.batch.listener.FirstJobListener;
import com.batch.listener.FirstStepListener;
import com.batch.processor.FirstItemProcessor;
import com.batch.reader.FirstItemReader;
import com.batch.service.SecondTasklet;
import com.batch.writer.FirstItemWriter;

@Configuration
public class SimpleJob {
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private SecondTasklet secondTasklet;
	
	@Autowired
	private FirstJobListener firstJobListener;
	
	@Autowired
	private FirstStepListener firstStepListener;
	
	@Autowired
	private FirstItemReader firstItemReader;
	
	@Autowired
	private FirstItemProcessor firstItemProcessor;
	
	@Autowired
	private FirstItemWriter firstItemWriter;
	
	@Bean
	public Job firstJob() {
		return jobBuilderFactory.get("First Job").
				incrementer(new RunIdIncrementer()).
				start(firstStep()).
				next(secondStep()).
				listener(firstJobListener).
				build();		
	}
	
	private Step firstStep() {
		return stepBuilderFactory.get("First Step").
				tasklet(firstTask()).
				listener(firstStepListener).
				build();
	}
	
	private Tasklet firstTask() {
		return new Tasklet() {
			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				System.out.println("This is first Tasklet Step ....");
				System.out.println("Job Execution Context in First Tasklet "+chunkContext.getStepContext().getJobExecutionContext());
				return RepeatStatus.FINISHED;
			}
		};
	}
	
	@Bean
	public Job secondJob() {
		return jobBuilderFactory.get("Second Job").
				incrementer(new RunIdIncrementer()).
				start(secondStep()).
				build();
	}

	private Step secondStep() {
		return stepBuilderFactory.get("Second Step").tasklet(secondTasklet).build();
	}
	
	@Bean
	public Job thirdJob() {
		return jobBuilderFactory.get("Third Job").
				incrementer(new RunIdIncrementer()).
				start(thirdStepFirstChunkStep()).
				build();
	}

	private Step thirdStepFirstChunkStep() {
		return stepBuilderFactory.get("First Chunk Step").
				<Integer,Long>chunk(3).
				reader(firstItemReader).
				processor(firstItemProcessor).
				writer(firstItemWriter).
				build();
	}

	

}
