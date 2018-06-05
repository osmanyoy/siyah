package com.java.tune.microbench;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.java.tune.User;

public class HashMapMB {
	public static void main(String[] args) {
		Map<String, User> myMap = new ConcurrentHashMap<>(20_000_000,0.9F,10_000);
		Map<Integer,String> strList2 = new HashMap<>();
		for (int i = 0; i < 20_000; i++) {
			String str = "osman" + i + "yaycioglu";
			strList2.put(i,str);
		}
		
		System.gc();
		try {
			Thread.sleep(2_000);
		} catch (Exception e) {
		}

		Map<Integer,String> strList = new HashMap<>();
		long delta = System.currentTimeMillis();
		for (int i = 0; i < 1_000_000; i++) {
			strList.put(i,"osman" + i);
		}
		System.out.println("Delta Write: " + (System.currentTimeMillis() - delta));
		
		delta = System.currentTimeMillis();
		for (int i = 0; i < 1_000_000; i++) {
			strList.containsKey(i);
		}
		System.out.println("Delta Get Object: " + (System.currentTimeMillis() - delta));

		delta = System.currentTimeMillis();
		for (int i = 0; i < 1_000_000; i++) {
			strList.get(i);
		}
		System.out.println("Delta Get: " + (System.currentTimeMillis() - delta));

		delta = System.currentTimeMillis();
		for (int i = 0; i < 1_000_000; i++) {
			strList.remove(i);
		}
		System.out.println("Delta Remove: " + (System.currentTimeMillis() - delta));

	}
}
