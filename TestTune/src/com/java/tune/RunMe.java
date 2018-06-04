package com.java.tune;

import java.util.ArrayList;
import java.util.List;

public class RunMe {
	
	public static List<User> users = new ArrayList<>();
	
	public static void main(String[] args) {
		for (int i = 0; i < 1_000_000_000; i++) {
			User user = new User();
			user.setName("user" + i);
			user.setSurname("Sur"+i);
			user.setAge(i);
			String str = user.myMethod();
			if (i % 1000 == 0) {
				System.out.println(str);
				users.add(user);
			}
			if (i % 10_000 == 0) {
				System.out.println("index : " + i);
			}
		}
		try {
			Thread.sleep(1_000_000_000L);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
