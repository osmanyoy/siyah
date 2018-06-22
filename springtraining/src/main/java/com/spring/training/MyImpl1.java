package com.spring.training;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("xyz")
public class MyImpl1 implements IMyInterface {

	@Override
	public String execute() {
		return "Execute 1";
	}
	
}
