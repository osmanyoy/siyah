package com.java.tune.multithread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Runner {
	public static void main(String[] args) {
		CountDownLatch downLatch = new CountDownLatch(5);
		List<MyThread1> myThread1s = new ArrayList<>();
		long delta = System.currentTimeMillis();
		for (int i = 0; i < 5; i++) {
			MyThread1 myThread1 = new MyThread1(downLatch,i);
			myThread1.start();
			myThread1s.add(myThread1);
		}
		
		try {
			downLatch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long mcounter=0;
		for (MyThread1 myThread1 : myThread1s) {
			mcounter += myThread1.getLocalCounter();
		}
		
		System.out.println(mcounter);
		System.out.println(MyThread1.counter);
		System.out.println("Delta : " + (System.currentTimeMillis() - delta));
	}
}
