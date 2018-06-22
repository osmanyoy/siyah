package com.spring.training.condition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.MultiValueMap;

public class MyConditionalClass implements Condition {
	
	@Override
	public boolean matches(ConditionContext context,
	                       AnnotatedTypeMetadata metadata) {
		MultiValueMap<String, Object> allAnnotationAttributes 
		= metadata.getAllAnnotationAttributes(MyCondition.class.getName());
		
		Integer type =(Integer) allAnnotationAttributes.getFirst("type");
		String property = context.getEnvironment().getProperty("com.conditional.test.type");
		if(property != null) {
			int parseInt = Integer.parseInt(property);
			if (parseInt == type) {
				return true;
			}
		}
		
		return false;
	}

}
