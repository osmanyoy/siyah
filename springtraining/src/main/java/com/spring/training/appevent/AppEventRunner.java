package com.spring.training.appevent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.stereotype.Component;

@Component
public class AppEventRunner implements CommandLineRunner{

	@Autowired
	private ApplicationEventMulticaster aem;
	
	@Override
	public void run(String... args) throws Exception {
		MyEvent myEvent = new MyEvent();
		myEvent.setDesc("o bu şu o");
		myEvent.setTest(100);
		
		aem.multicastEvent(myEvent);
		
	}

}
