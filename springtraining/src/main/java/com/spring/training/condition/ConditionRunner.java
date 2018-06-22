package com.spring.training.condition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConditionRunner implements CommandLineRunner{
	
	private static Logger logger = LoggerFactory.getLogger(ConditionRunner.class); 
	
	@Autowired
	private MyBean mBean;
	
	@Override
	public void run(String... args) throws Exception {
		logger.info("MyBean : " + mBean.getBeanField1());
	}

}
