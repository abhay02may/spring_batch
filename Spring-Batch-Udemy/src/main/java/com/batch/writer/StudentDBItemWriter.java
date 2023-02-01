package com.batch.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.batch.model.StudentJdbc;

@Component
public class StudentDBItemWriter implements ItemWriter<StudentJdbc>{
	
	@Override
	public void write(List<? extends StudentJdbc> items) throws Exception {
		System.out.println("Inside StudentDBItemWriter class");
		items.stream().forEach(System.out::println);
	}

}
