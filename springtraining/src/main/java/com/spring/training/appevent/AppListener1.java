package com.spring.training.appevent;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AppListener1 implements ApplicationListener<MyEvent>{

	@Override
	public void onApplicationEvent(MyEvent event) {
		System.out.println("AppListener1 : " + event.getDesc());
	}

}
