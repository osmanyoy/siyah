package com.spring.training.rest;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
public class RestConfig {

	@Bean
	public Docket createSwagger() {
		return new Docket(DocumentationType.SWAGGER_2).select()
		                                              .apis(RequestHandlerSelectors.any())
		                                              .paths(PathSelectors.any())
		                                              .build();
	}

}
