package com.spring.training.property;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyPropExecution implements CommandLineRunner {

	private static Logger logger = LoggerFactory.getLogger(MyPropExecution.class);

	@Autowired
	private MyPropReadObject mProp;

	@Override
	public void run(String... args) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("mProp : "
			             + mProp);
		}
	}

}
