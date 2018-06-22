package com.spring.training.appevent;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;

@Configuration
public class AppEventConfig {

	@Bean
	public Executor myThreadPool() {
		return Executors.newFixedThreadPool(5);
	}
	
	@Autowired
	public void configMulticaster(ApplicationEventMulticaster aem) {
		SimpleApplicationEventMulticaster eventMulticaster = (SimpleApplicationEventMulticaster) aem;
		eventMulticaster.setTaskExecutor(myThreadPool());
	}
	
}
