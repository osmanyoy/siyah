package com.spring.training.actuator;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

import com.spring.training.rest.ErrorObj;

@Endpoint(id="osman")
@Component
public class MyActuatorEndpoint {

	@ReadOperation
	public ErrorObj errorInfo() {
		ErrorObj errorObj = new ErrorObj();
		errorObj.setCode(929);
		errorObj.setDesc("Error Test");
		return errorObj;
	}
	
	@WriteOperation
	public ErrorObj writeErrorInfo(String desc) {
		ErrorObj errorObj = new ErrorObj();
		errorObj.setCode(929);
		errorObj.setDesc(desc);
		return errorObj;
	}

}
