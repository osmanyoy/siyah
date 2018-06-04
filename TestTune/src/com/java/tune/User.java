package com.java.tune;

import java.util.UUID;

public class User {
	private String name;
	private String surname;
	private int age;
	
	public String myMethod() {
		return UUID.randomUUID().toString();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
	
}
