package com.spring.training;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import com.test.AnotherConfig;

@Configuration
@PropertySource("classpath:my.configuration")
@Import(AnotherConfig.class)
public class MyConfig {

	@Bean("myFuncImpl")
	@Qualifier("function")
	public IMyInterface createMyInterface(@Value("${my.interface.impl}") int index) {
		switch (index) {
		case 1:
			return new MyImpl1();
		case 2:
			return new MyImpl2();
		case 3:
			return new MyImpl3();
		default:
			return new MyImpl1();
		}
	}

	@Bean("myFuncImpl2")
	@Qualifier("function2")
	public IMyInterface createMyInterface2(@Value("#{myBean.getValue('my.interface.impl')}") int index) {
		switch (index) {
		case 1:
			return new MyImpl1();
		case 2:
			return new MyImpl2();
		case 3:
			return new MyImpl3();
		default:
			return new MyImpl1();
		}
	}
	
}
