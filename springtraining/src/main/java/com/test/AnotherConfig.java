package com.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class AnotherConfig {

	@Autowired
	private Environment env;

	@Bean
	public TestComponent testComponent() {
		String serverPort = env.getProperty("server.port");
		// DB
		TestComponent testComponent = new TestComponent(Integer.parseInt(serverPort));
		testComponent.setStr("osman");
		return testComponent;
	}
}
