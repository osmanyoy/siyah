package com.spring.training;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class MyThirdObj implements BeanNameAware, InitializingBean {

	@Autowired
	@Qualifier("xyz")
	private IMyInterface test;

	private String str;

	public MyThirdObj() {
	}

	@PostConstruct
	public void init() {
		str = test.execute();
	}

	@PreDestroy
	public void dest() {
		System.out.println("destroy");
	}

	@Override
	public void setBeanName(String name) {

	}

	@Override
	public void afterPropertiesSet() throws Exception {

	}

}
