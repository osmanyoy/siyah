package com.spring.training.aop;

import org.springframework.stereotype.Component;


public class Callee {
	
	public String hello(String name) {
		return "hello " + name;
	}
	
	@MyLogAnno(type="INFO")
	public String test(String name) {
		return "test " + name;
	}
	
}
