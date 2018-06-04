package com.java.tune.microbench;

public class StringMicroBenchmark2 {
	public static void main(String[] args) {

		for (int i = 0; i < 20_000; i++) {
			StringBuilder builder = new StringBuilder(50);
			builder.append("osman");
			builder.append(i);
			builder.append("yaycioglu");
			builder.append("test");
		}
		
		System.gc();
		try {
			Thread.sleep(1_000);
		} catch (Exception e) {
		}
		 
		long delta1 = System.nanoTime();
		for (int i = 0; i < 1_000; i++) {
			StringBuilder builder = new StringBuilder(50);
			builder.append("osman");
			builder.append(i);
			builder.append("yaycioglu");
			builder.append("test");
		}
		System.out.println("Delta Nano : " + (System.nanoTime() - delta1));


		long delta = System.currentTimeMillis();
		for (int i= 0; i < 1_000_000; i++) {
			StringBuilder builder = new StringBuilder(50);
			builder.append("osman");
			builder.append(i);
			builder.append("yaycioglu");
			builder.append("test");
		}
		System.out.println("Delta : " + (System.currentTimeMillis() - delta));
	}
}
