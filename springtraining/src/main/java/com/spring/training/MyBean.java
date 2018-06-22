package com.spring.training;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class MyBean {
	
	@Autowired
	private Environment env;
	
	
	public int getValue(String propName) {
		return Integer.parseInt(env.getProperty(propName));
	}
	
}
