package com.spring.training.jpa;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class MyJPATestConverter implements AttributeConverter<MyJPATest, String>{

	@Override
	public String convertToDatabaseColumn(MyJPATest attribute) {
		return attribute.getTest1() + "," + attribute.getTest2();
	}

	@Override
	public MyJPATest convertToEntityAttribute(String dbData) {
		String[] split = dbData.split(",");
		MyJPATest jpaTest = new MyJPATest();
		jpaTest.setTest1(split[0]);
		jpaTest.setTest2(split[1]);
		return jpaTest;
	}

}
