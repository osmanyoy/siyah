package com.spring.training.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// @Configuration
// @EnableGlobalMethodSecurity(prePostEnabled = true)
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

	public void initSecurity2(final AuthenticationManagerBuilder builder) {
		BCryptPasswordEncoder passwordEncoder = this.passwordEncoder();
		try {
			builder.inMemoryAuthentication()
			       .passwordEncoder(passwordEncoder)
			       .withUser("osman")
			       .password(passwordEncoder.encode("1234"))
			       .roles("ADMIN")
			       .and()
			       .withUser("ali")
			       .password(passwordEncoder.encode("9876"))
			       .roles("USER");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
