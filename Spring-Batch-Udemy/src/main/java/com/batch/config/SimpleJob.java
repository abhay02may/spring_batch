package com.batch.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.batch.listener.FirstJobListener;
import com.batch.listener.FirstStepListener;
import com.batch.model.StudentCSV;
import com.batch.model.StudentJdbc;
import com.batch.model.StudentJson;
import com.batch.model.StudentResponse;
import com.batch.model.StudentXml;
import com.batch.processor.FirstItemProcessor;
import com.batch.reader.ApiItemAdapterReader;
import com.batch.reader.CSVFileItemReader;
import com.batch.reader.FirstItemReader;
import com.batch.reader.StudenDBItemReader;
import com.batch.reader.XmlStudentFileItemReader;
import com.batch.service.SecondTasklet;
import com.batch.writer.ApiAdapterWriter;
import com.batch.writer.CSVFileItemWriterDemo;
import com.batch.writer.CSVFileWriter;
import com.batch.writer.FirstItemWriter;
import com.batch.writer.JsonStudentFileItemWriter;
import com.batch.writer.StudentDBItemWriter;
import com.batch.writer.StudentXmlFileItemWriter;

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
	
	@Autowired
	private CSVFileItemReader csvFileItemReader;
	
	@Autowired 
	private CSVFileItemWriterDemo csvFileItemWriterDemo;
	
	@Autowired
	private XmlStudentFileItemReader xmlStudentFileItemReader;
	
	@Autowired
	private JsonStudentFileItemWriter jsonStudentFileItemWriter;
	
	@Autowired
	private StudentXmlFileItemWriter studentXmlFileItemWriter;
	
	@Autowired 
	private StudenDBItemReader studenDBItemReader;
	
	@Autowired
	private StudentDBItemWriter studentDBItemWriter;
	
	@Autowired
	private ApiItemAdapterReader apitemAdapterReader;
	
	@Autowired
	private ApiAdapterWriter apiAdapterWriter;
	
	@Autowired
	private CSVFileWriter csvFileWriter;
	
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	@Primary
	public DataSource datasource() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	@ConfigurationProperties(prefix = "spring.studentdatasource")
	public DataSource studentdatasource() {
		return DataSourceBuilder.create().build();
	}
	
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
	
	@Bean
	public Job fourthJob() {
		return jobBuilderFactory.get("Fourth Job").
		incrementer(new RunIdIncrementer()).
		start(csvReaderStep()).build();
	}

	private Step csvReaderStep() {
		return stepBuilderFactory.get("Second Chunk Step").
				<StudentCSV,StudentCSV>chunk(3).
				reader(csvFileItemReader.flatFileItemReader()).
				writer(csvFileItemWriterDemo).
				build();
	}
	
	
	@Bean
	public Job fifthJob() {
		return jobBuilderFactory.get("Fifth Job").incrementer(new RunIdIncrementer()).start(jsonReaderStep()).build();
	}

	private Step jsonReaderStep() {
		return stepBuilderFactory.get("Third Chunk Step").
				<StudentJson,StudentJson>chunk(3).
				reader(jsonItemReader(null)).
				writer(jsonStudentFileItemWriter).build();
	}
	
	@Bean
	@StepScope
	public JsonItemReader<StudentJson> jsonItemReader(@Value("#{jobParameters['inputJsonFile']}") FileSystemResource fileSystemResource ) {
		
		JsonItemReader<StudentJson> jsonItemReader=new JsonItemReader<StudentJson>();
		jsonItemReader.setResource(fileSystemResource);
		jsonItemReader.setJsonObjectReader(new JacksonJsonObjectReader<>(StudentJson.class));
		//jsonItemReader.setCurrentItemCount(2);// Based on the value set here it will start reading from that line, by default its value is 0
		//jsonItemReader.setMaxItemCount(9); // It will read till 9 line only as Max Item count is set as 9
		return jsonItemReader;
	}
	
	@Bean
	public Job sixthJob() {
		return jobBuilderFactory.get("Sixth Job").
				incrementer(new RunIdIncrementer()).
				start(xmlReaderStep()).build();
	}
	
	private Step xmlReaderStep() {
		return stepBuilderFactory.get("XML Step").
				<StudentXml,StudentXml>chunk(3).
				reader(xmlStudentFileItemReader.staxEventItemReader()).
				writer(studentXmlFileItemWriter).build();
	}
	
	@Bean
	public Job JdbcJob() {
		return jobBuilderFactory.get("JDBC Job").
				incrementer(new RunIdIncrementer()).
				start(jdbcReaderStep()).build();
	}
	
	private Step jdbcReaderStep() {
		return stepBuilderFactory.get("JDBC Step").
				<StudentJdbc,StudentJdbc>chunk(3).
				reader(studenDBItemReader.jdbcCursorItemReader()).
				writer(csvFileWriter.flatFileItemWriter()).build();
	}

	/*@Bean
	public Job apiJob() {
		return jobBuilderFactory.get("API Job").
				incrementer(new RunIdIncrementer()).
				start(apiReaderStep()).build();
	}
	
	private Step apiReaderStep() {
		return stepBuilderFactory.get("API Step").
				<StudentResponse,StudentResponse>chunk(3).
				reader(apitemAdapterReader.itemReaderAdapter()).
				writer(apiAdapterWriter).build();
	}*/

	
	

}
