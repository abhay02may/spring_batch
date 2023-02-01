package com.batch.writer;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;

import org.springframework.batch.item.file.FlatFileFooterCallback;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import com.batch.model.StudentJdbc;

@Component
public class CSVFileWriter {
	
	
	public FlatFileItemWriter<StudentJdbc>  flatFileItemWriter(){
		FlatFileItemWriter<StudentJdbc>  flatFileItemWriter=new FlatFileItemWriter<StudentJdbc>();
		File outFile=new File("D:\\Development\\sts-workspace\\Spring-Batch-Udemy\\src\\main\\resources\\OutputFile\\Student.csv");
		flatFileItemWriter.setResource(new FileSystemResource(outFile));
		
		flatFileItemWriter.setHeaderCallback(new FlatFileHeaderCallback() {
			@Override
			public void writeHeader(Writer writer) throws IOException {
				writer.write("Student Id,First Name,Last Name,Email");
			}
		});
		
		flatFileItemWriter.setLineAggregator(new DelimitedLineAggregator<StudentJdbc>() {
			{
				setFieldExtractor(new BeanWrapperFieldExtractor<StudentJdbc>() {
					{
						setNames(new String[] {
								"studentId","firstName","lastName","email"
						});
					}
				});
			}
		});
		
		flatFileItemWriter.setFooterCallback(new FlatFileFooterCallback() {
			@Override
			public void writeFooter(Writer writer) throws IOException {
				writer.write("Created @ : "+new Date());
			}
		});
		
		
		return flatFileItemWriter;
	}

}
