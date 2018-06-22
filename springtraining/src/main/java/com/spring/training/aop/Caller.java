package com.spring.training.aop;

import org.springframework.beans.factory.annotation.Autowired;

public class Caller {
	
	@Autowired
	private Callee callee;
	
	
	public void start() {
		String hello = callee.hello("osman");
		System.out.println("---------" + hello);
		
		String test = callee.test("osman");
		System.out.println("---------test annotation ---------" + test);
		
		
	}
	

}
