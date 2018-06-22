package com.spring.training.rest;

import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyRestAdvice {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ErrorObj handleBeanValidationError(MethodArgumentNotValidException notValidException) {
		ErrorObj errorObj = new ErrorObj();
		BindingResult bindingResult = notValidException.getBindingResult();
		List<FieldError> fieldErrors = bindingResult.getFieldErrors();
		String str = "";
		for (FieldError fieldError : fieldErrors) {
			str += "field : " + fieldError.getField() + " msg : " + fieldError.getDefaultMessage();
		}
		errorObj.setDesc(str);
		errorObj.setCode(200);
		return errorObj;
	}
	
}
