package com.spring.training;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SpringtrainingApplication {

	@Autowired
	private MyFirstBean myFirstBean;

	@Autowired
	@Qualifier("abc")
	private IMyInterface myImpl1;
	
	@Autowired
	@MyImplQualifier
	private IMyInterface test;
	

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringtrainingApplication.class, args);

		SpringtrainingApplication application = context.getBean(SpringtrainingApplication.class);

		// Wrong usage
		// SpringtrainingApplication application = new SpringtrainingApplication();
		MyFirstBean myFirstBean2 = application.getMyFirstBean();
		System.out.println("Result " + myFirstBean2.getStr());
		System.out.println("execute : " + application.myImpl1.execute());
		System.out.println("execute : " + application.test.execute());
	}

	public MyFirstBean getMyFirstBean() {
		return myFirstBean;
	}

}
