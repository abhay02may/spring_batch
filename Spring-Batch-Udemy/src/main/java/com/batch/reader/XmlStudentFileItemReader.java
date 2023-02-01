package com.batch.reader;

import java.io.File;

import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;

import com.batch.model.StudentXml;

@Component
public class XmlStudentFileItemReader {
	
	public StaxEventItemReader<StudentXml> staxEventItemReader(){
		StaxEventItemReader<StudentXml> xmlItemReader=new StaxEventItemReader<StudentXml>();
		File xmlFile=new File("D:\\Development\\sts-workspace\\Spring-Batch-Udemy\\src\\\\main\\resources\\InputFiles\\Student.xml");
		xmlItemReader.setResource(new FileSystemResource(xmlFile));
		
		xmlItemReader.setFragmentRootElementName("student");
		xmlItemReader.setUnmarshaller(new Jaxb2Marshaller() {
			{
				setClassesToBeBound(StudentXml.class);
			}
		});
		
		return xmlItemReader;
	}
	
	

}
