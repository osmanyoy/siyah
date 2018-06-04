package com.java.tune.microbench;

public class StringMicroBenchmark {
	public static void main(String[] args) {

		for (int i = 0; i < 20_000; i++) {
			String str = "osman" + i + "yaycioglu";
		}
		
		System.gc();
		try {
			Thread.sleep(1_000);
		} catch (Exception e) {
		}

		long delta1 = System.nanoTime();
		for (int i = 0; i < 1_000; i++) {
			String str = "osman" + i + "yaycioglu";
			str += "test";
		}
		System.out.println("Delta nano : " + (System.nanoTime() - delta1));
		
		
		long delta = System.currentTimeMillis();
		for (int i = 0; i < 1_000_000; i++) {
			String str = "osman" + i + "yaycioglu";
			str += "test";
		}
		System.out.println("Delta : " + (System.currentTimeMillis() - delta));
	}
}
