package com.spring.training.condition;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConditionConfig {

	
	@Bean
	@MyCondition(type=1)
	public MyBean myBean1(){
		MyBean myBean = new MyBean();
		myBean.setBeanField1("Option1");
		return myBean;
	}
	
	@Bean
	@MyCondition(type=2)
	public MyBean myBean2(){
		MyBean myBean = new MyBean();
		myBean.setBeanField1("Option2");
		return myBean;
	}
	


}
