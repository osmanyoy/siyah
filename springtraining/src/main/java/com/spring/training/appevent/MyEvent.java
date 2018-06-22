package com.spring.training.appevent;

import org.springframework.context.ApplicationEvent;

public class MyEvent extends ApplicationEvent{

	private static final long serialVersionUID = 822276459794150401L;

	private String desc;
	private int test;
	
	
	public MyEvent() {
		super(MyEvent.class);
	}


	public String getDesc() {
		return desc;
	}


	public void setDesc(String desc) {
		this.desc = desc;
	}


	public int getTest() {
		return test;
	}


	public void setTest(int test) {
		this.test = test;
	}
	
	

}
