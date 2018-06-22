package com.spring.training.microbench;

public class StringTest {
	public static void main(String[] args) {
		String ekle = "osman1";
		long delta = System.currentTimeMillis();
		for (int i = 0; i < 1_000_000; i++) {
			// String.format("test deneme %s index %d" , "osman1",i);
			
//			String string = "test deneme " + ekle +   " index " + i;
//			string += "test ekle" + ekle;
			
			StringBuilder stringBuilder = new StringBuilder(100);
			stringBuilder.append("test deneme ");
			stringBuilder.append(ekle);
			stringBuilder.append(" index ");
			stringBuilder.append(i);
			stringBuilder.append("test ekle");
			stringBuilder.append(ekle);
			String string2 = stringBuilder.toString();
		}
		
		System.out.println("Delta : " + (System.currentTimeMillis() - delta));
	}
}
