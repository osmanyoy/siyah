package com.spring.training;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@MyImplQualifier
public class MyImpl3 implements IMyInterface {

	@Override
	public String execute() {
		return "Execute 3";
	}
	
}
