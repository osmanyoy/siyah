package com.spring.training.conc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockUsage {
	private List<String> strList = new ArrayList<>();
	private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	
	public synchronized void add (String str) {
		strList.add(str);
	}
	
	public synchronized void get(int i) {
		strList.get(i);
	}

	public void add2 (String str) {
		readWriteLock.writeLock().lock();
		try {
			strList.add(str);
			
		} finally {
			readWriteLock.writeLock().unlock();
		}
	}
	
	public void get2(int i) {
		readWriteLock.readLock().lock();
		try {
			strList.get(i);
		} finally {
			readWriteLock.readLock().unlock();
		}
	}

}
