package com.spring.training.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Lazy;

@Configuration
@EnableAspectJAutoProxy
public class AOPConfig {

	@Bean
	public Callee callee() {
		return new Callee();
	}
	
	@Bean
	public Caller caller() {
		return new Caller();
	}

}
