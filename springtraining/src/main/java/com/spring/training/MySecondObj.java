package com.spring.training;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class MySecondObj {
	
	@Qualifier("xyz")
	@Autowired
	private IMyInterface fieldWire;
	
	@Autowired
	public MySecondObj(@Qualifier("abc") IMyInterface myInterface) {
		System.out.println("MySecondObj : " +  myInterface.execute());
	}
	
	@Autowired
	public void testMethodWiring(@Qualifier("xyz") IMyInterface myInterface) {
		System.out.println("My metod wiring : " +  myInterface.execute());
	}
	
	@Autowired
	public void testMethodWiring2(@Qualifier("abc") IMyInterface myInterface) {
		System.out.println("My metod wiring 2 : " +  myInterface.execute());
	}

}
