package com.spring.training.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService createUserDetailsService() {
		return new MyUserDetailService(this.passwordEncoder());
	}

	@Bean
	public MySecurityAccess secAccess() {
		return new MySecurityAccess();
	}

	@Autowired
	public void initSecurity(final AuthenticationManagerBuilder builder) {
		try {
			builder.userDetailsService(this.createUserDetailsService())
			       .passwordEncoder(this.passwordEncoder());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
