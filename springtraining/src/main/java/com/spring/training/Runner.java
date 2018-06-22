package com.spring.training;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class Runner {
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringtrainingApplication.class, args);
		
		SpringtrainingApplication application = context.getBean(SpringtrainingApplication.class);
		
		// Wrong usage
		//SpringtrainingApplication application = new SpringtrainingApplication();
		MyFirstBean myFirstBean2 = application.getMyFirstBean();
		System.out.println("Result " + myFirstBean2.getStr());
	}
}
