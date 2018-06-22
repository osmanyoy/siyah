package com.spring.training.property;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
public class PropConfig {
	
	@Bean
	public MyPropReadObject myPropReadObject() {
		return new MyPropReadObject();
	}
}
