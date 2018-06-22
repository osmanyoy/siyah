package com.spring.training.conc;

import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.util.concurrent.ListenableFuture;

public class MyAsyncBean {
	
	@Async
	public Future<String> testAsync() {
		try {
			Thread.sleep(1_000);
		} catch (Exception e) {
		}
		return AsyncResult.forValue("test result");
	}
	
	@Async
	public ListenableFuture<String> testAsync2() {
		try {
			Thread.sleep(1_000);
		} catch (Exception e) {
		}
		return AsyncResult.forValue("test result");
	}
	
}
