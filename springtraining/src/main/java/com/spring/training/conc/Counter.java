package com.spring.training.conc;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter {
	private int count;
	private AtomicInteger atomicInteger = new AtomicInteger(0);
	private Lock lock = new ReentrantLock();
	
	public synchronized void increase() {
		count++;
	}

	public void increase2() {
		atomicInteger.incrementAndGet();
	}
	
	public void increase3() {
		try {
			lock.lock();
			count++;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

}
