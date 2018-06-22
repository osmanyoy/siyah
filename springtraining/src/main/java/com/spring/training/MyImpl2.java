package com.spring.training;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
// @Primary
@Qualifier("abc")
public class MyImpl2 implements IMyInterface {

	@Override
	public String execute() {
		return "Execute 2";
	}
	
}
