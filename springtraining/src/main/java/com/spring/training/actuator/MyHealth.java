package com.spring.training.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import com.spring.training.rest.ErrorObj;

@Component
public class MyHealth implements HealthIndicator{

	@Override
	public Health health() {
		ErrorObj errorObj = new ErrorObj();
		errorObj.setCode(1012);
		errorObj.setDesc("File not found : shjsdh.txt");
		return Health.down().withDetail("error", errorObj).build();
	}

}
