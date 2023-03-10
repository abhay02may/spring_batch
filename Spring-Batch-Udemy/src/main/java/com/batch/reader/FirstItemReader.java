package com.batch.reader;



import java.util.Arrays;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

@Component
public class FirstItemReader implements ItemReader<Integer>{
	
	List<Integer> list=Arrays.asList(1,2,3,4,5,6,7,8,9,10);
	int index=0;

	@Override
	public Integer read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		Integer item=null;
		if(index<list.size()) {
			item=list.get(index);
			index++;
			System.out.println(" From Item Reader returning "+item);
			return item;
		}
		index=0;
		return null;
	}
	

}
