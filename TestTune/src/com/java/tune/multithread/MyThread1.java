package com.java.tune.multithread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

public class MyThread1 extends Thread {

	public static long counter;

	public static AtomicLong counterAtomic = new AtomicLong();

	private CountDownLatch downLatch;

	public MyThread1(CountDownLatch downLatch,int index) {
		setName("MyThread-" + index);
		this.downLatch = downLatch;
	}

	public static synchronized void increase() {
		counter++;
	}
	
	private long localCounter = 0;
	
	@Override
	public void run() {
		for (long i = 0; i < 100_000_000_000L; i++) {
			localCounter++;
			if (localCounter % 500_000 == 0) {
				try {
					Thread.sleep(1);
				} catch (Exception e) {
				}
			}
			// increase();
			// counterAtomic.incrementAndGet();
			
		}
		this.downLatch.countDown();
		// while(true) {
		// try {
		// } catch (Exception e) {
		// }
		// }
	}

	public long getLocalCounter() {
		return localCounter;
	}

	public void setLocalCounter(long localCounter) {
		this.localCounter = localCounter;
	}

}
