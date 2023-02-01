package com.batch.reader;

import javax.sql.DataSource;

import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import com.batch.model.StudentJdbc;

@Component
public class StudenDBItemReader {
	
	@Autowired
	private DataSource studentdatasource;
	
	public JdbcCursorItemReader<StudentJdbc> jdbcCursorItemReader(){
		JdbcCursorItemReader<StudentJdbc> jdbcCursorItemReader=new JdbcCursorItemReader<StudentJdbc>();
		jdbcCursorItemReader.setDataSource(studentdatasource);
		String sql="SELECT student_id AS studentId,first_name as firstName,last_name as lastName,email from abhaydb.student";
		jdbcCursorItemReader.setSql(sql);
		
		jdbcCursorItemReader.setRowMapper(new BeanPropertyRowMapper<StudentJdbc>() {
			{
				setMappedClass(StudentJdbc.class);
			}
		});
		
		return jdbcCursorItemReader;
	}

}
