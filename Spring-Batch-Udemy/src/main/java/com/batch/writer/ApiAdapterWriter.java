package com.batch.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.batch.model.StudentResponse;

@Component
public class ApiAdapterWriter implements ItemWriter<StudentResponse> {

	@Override
	public void write(List<? extends StudentResponse> items) throws Exception {
		System.out.println("Inside ApiAdapterWriter class");
		items.stream().forEach(System.out::println);		
	}

}
