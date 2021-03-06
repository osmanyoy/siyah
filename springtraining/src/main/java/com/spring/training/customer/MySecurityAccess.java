package com.spring.training.customer;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class MySecurityAccess extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http.cors()
		    .disable()
		    .authorizeRequests()
		    .antMatchers("/user/**")
		    .anonymous()
		    .antMatchers("/**")
		    .authenticated()
		    .and()
		    .httpBasic();
	}

}
