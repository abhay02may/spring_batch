package com.batch.reader;

import java.io.File;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import com.batch.model.StudentCSV;

@Component
public class CSVFileItemReader {

	public FlatFileItemReader<StudentCSV> flatFileItemReader() {

		FlatFileItemReader<StudentCSV> flatFileItemReader = new FlatFileItemReader<StudentCSV>();

		flatFileItemReader.setResource(new FileSystemResource(new File(
				"D:\\Development\\sts-workspace\\Spring-Batch-Udemy\\src\\main\\resources\\InputFiles\\Student.csv")));// Refer Line 47 to pass the path as run time argument

		flatFileItemReader.setLineMapper(new DefaultLineMapper<StudentCSV>() {
			{
				setLineTokenizer(new DelimitedLineTokenizer() {
					{
						setNames("StudentId", "First Name", "Last Name", "Email");
						// setDelimiter("|"); Like this we can change the delimeter of the file if
						// required
					}
				});

				setFieldSetMapper(new BeanWrapperFieldSetMapper<StudentCSV>() {
					{
						setTargetType(StudentCSV.class);
					}

				});
			}
		});

		flatFileItemReader.setLinesToSkip(1);
		return flatFileItemReader;
	}
	
	/*We can pass this path as a run time argument using below steps:
	 * Step 1: Add the path as run time arguments in argument such as iputFile=D:\\Development\\sts-workspace\\Spring-Batch-Udemy\\src\\main\\resources\\InputFiles\\Student.csv
	 * Step 2: Add @Bean and @StepScope annotation to this method
	 * Step 3: Add Argument to this method as 
	 * 	FlatFileItemReader<StudentCSV> flatFileItemReader(@Value("#{jobParameters['inputFile']}") FileSystemResource fileSystemResource)
	 * Step 4: Directly used the fileSystemResource to set the resources as:
	 *   flatFileItemReader.setResource(fileSystemResource);
	 */

}
