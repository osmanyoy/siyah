package com.spring.training.rest;

public class MyValidationException extends Exception{
	
	private static final long serialVersionUID = 5750844038087313904L;

	private String desc;
	private int code;
	
	public MyValidationException(String desc,
	                             int code) {
		super();
		this.desc = desc;
		this.code = code;
	}
	
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	
}
