package com.batch.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.batch.model.StudentXml;

@Component
public class StudentXmlFileItemWriter implements ItemWriter<StudentXml>{

	@Override
	public void write(List<? extends StudentXml> items) throws Exception {
		System.out.println("Inside StudentXmlFileItemWriter class");
		items.stream().forEach(System.out::println);		
	}

}
