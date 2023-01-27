package com.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class FirstItemProcessor implements ItemProcessor<Integer, Long>{

	@Override
	public Long process(Integer item) throws Exception {
		System.out.println(" Going to process item : "+item);
		Long value= (long) (item +100);
		System.out.println(" Processed item : "+value);
		return value;
	}

}
