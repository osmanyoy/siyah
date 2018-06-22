package com.spring.training.customer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sectest")
public class TestRest {

	@GetMapping("hello")
	@MySecurity(role = "ADMIN")
	public String hello() {
		return "hello";
	}

}
