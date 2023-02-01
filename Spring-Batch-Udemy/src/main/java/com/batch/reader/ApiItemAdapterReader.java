package com.batch.reader;

import org.springframework.batch.item.adapter.ItemReaderAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.batch.model.StudentResponse;
import com.batch.service.StudentService;

@Component
public class ApiItemAdapterReader {
	
	@Autowired
	private StudentService studentService;
	
	public ItemReaderAdapter<StudentResponse> itemReaderAdapter(){
		ItemReaderAdapter<StudentResponse> itemReaderAdapter=new ItemReaderAdapter<StudentResponse>();
		itemReaderAdapter.setTargetObject(studentService);
		itemReaderAdapter.setTargetMethod("getStudent");
		return itemReaderAdapter;
	}

}
