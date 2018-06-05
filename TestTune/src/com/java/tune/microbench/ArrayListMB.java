package com.java.tune.microbench;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ArrayListMB {
	public static void main(String[] args) {
		
		List<String> strList2 = new ArrayList<>();
		for (int i = 0; i < 20_000; i++) {
			String str = "osman" + i + "yaycioglu";
			strList2.add(str);
		}
		
		System.gc();
		try {
			Thread.sleep(2_000);
		} catch (Exception e) {
		}

		List<String> strList = new ArrayList<>();
		long delta = System.currentTimeMillis();
		for (int i = 0; i < 1_000_000; i++) {
			strList.add("osman" + i);
		}
		System.out.println("Delta Write: " + (System.currentTimeMillis() - delta));
		
		delta = System.currentTimeMillis();
		for (int i = 0; i < 100_000; i++) {
			strList.contains("osman" + i);
		}
		System.out.println("Delta Get Object: " + (System.currentTimeMillis() - delta));

		delta = System.currentTimeMillis();
		for (int i = 0; i < 1_000_000; i++) {
			strList.get(i);
		}
		System.out.println("Delta Get: " + (System.currentTimeMillis() - delta));

		delta = System.currentTimeMillis();
		for (int i = 0; i < 1_000_000; i++) {
			strList.remove(0);
		}
		System.out.println("Delta Remove: " + (System.currentTimeMillis() - delta));

	}
}
