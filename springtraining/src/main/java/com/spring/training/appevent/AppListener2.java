package com.spring.training.appevent;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AppListener2 {

	@EventListener
	public void handleEvent(MyEvent event) {
		System.out.println("AppListener1 : " + event.getDesc());
	}

}
