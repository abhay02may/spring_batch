package com.batch.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.batch.model.StudentResponse;

@Service
public class StudentService {
	
	public List<StudentResponse> getStudents(){
		return Arrays.asList(
				new StudentResponse(1L, "John", "Smith", "john@gmail.com"),
				new StudentResponse(2L, "Sachin", "Dave", "sachin@gmail.com"),
				new StudentResponse(3L, "Peter", "Mark", "peter@gmail.com"),
				new StudentResponse(4L, "Martin", "Smith", "martin@gmail.com"),
				new StudentResponse(5L, "Raj", "Patel", "raj@gmail.com"),
				new StudentResponse(6L, "Virat", "Yadav", "virat@gmail.com"),
				new StudentResponse(7L, "Prabhas", "Shirke", "prabhas@gmail.com"),
				new StudentResponse(8L, "Tina", "Kapoor", "tina@gmail.com"),
				new StudentResponse(9L, "Mona", "Sharma", "mona@gmail.com"),
				new StudentResponse(10L, "Rahul", "Varma", "rahul@gmail.com"));
	}

}
