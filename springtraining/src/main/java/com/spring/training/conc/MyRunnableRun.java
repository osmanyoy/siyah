package com.spring.training.conc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class MyRunnableRun {
	public static void main(String[] args) {
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(5);
		MyRunnable myRunnable = new MyRunnable();
		newFixedThreadPool.execute(myRunnable);
		
		MyCallable myCallable = new MyCallable();
		
		Future<String> submit = newFixedThreadPool.submit(myCallable);
		// extra işlemler
		try {
			String string = submit.get();
			String string2 = submit.get(10_000,TimeUnit.MILLISECONDS);
			
			if (submit.isDone()) {
				String string3 = submit.get();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
