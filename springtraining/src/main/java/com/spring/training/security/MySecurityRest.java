package com.spring.training.security;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.SessionScope;

@RestController
@RequestMapping("/sec")
@SessionScope
public class MySecurityRest {

	public MySecurityRest() {
		System.out.println("oluştum");
	}

	@GetMapping("hello")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String hello() {
		return "hello security";
	}

	@GetMapping("hello2")
	public String hello2(final Principal principal,
	                     final HttpServletRequest hsr) {
		Authentication authentication = SecurityContextHolder.getContext()
		                                                     .getAuthentication();
		String name = principal.getName();
		System.out.println(name);
		return "hello security";
	}

}
