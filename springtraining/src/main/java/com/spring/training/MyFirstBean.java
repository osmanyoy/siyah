package com.spring.training;

import org.springframework.stereotype.Component;

@Component
public class MyFirstBean {
	
	private String str = "osman";

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}
	
	
}
