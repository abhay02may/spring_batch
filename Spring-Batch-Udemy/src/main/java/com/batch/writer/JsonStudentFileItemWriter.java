package com.batch.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.batch.model.StudentJson;

@Component
public class JsonStudentFileItemWriter implements ItemWriter<StudentJson> {

	@Override
	public void write(List<? extends StudentJson> items) throws Exception {
		System.out.println("Inside JsonStudentFileItemWriter class");
		items.stream().forEach(System.out::println);
	}

}
