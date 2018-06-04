package com.java.tune.microbench;

public class StringMicroBenchmark3 {
	public static void main(String[] args) {

		for (int i = 0; i < 20_000; i++) {
			String.format("osman%d%s%s", i,"yaycioglu","test");
		}
		
		System.gc();
		try {
			Thread.sleep(1_000);
		} catch (Exception e) {
		}
		 
		long delta1 = System.nanoTime();
		for (int i = 0; i < 1_000; i++) {
			String.format("osman%d%s%s", i,"yaycioglu","test");
		}
		System.out.println("Delta Nano : " + (System.nanoTime() - delta1));


		long delta = System.currentTimeMillis();
		for (int i= 0; i < 1_000_000; i++) {
			String.format("osman%d%s%s", i,"yaycioglu","test");
		}
		System.out.println("Delta : " + (System.currentTimeMillis() - delta));
	}
}
