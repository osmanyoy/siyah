package com.spring.training.conc;

import java.text.SimpleDateFormat;

public class MyThread extends Thread {

	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public void run() {
		while (true) {
			try {
				// işlem
			} catch (Exception e) {
			}
		}
	}
}
