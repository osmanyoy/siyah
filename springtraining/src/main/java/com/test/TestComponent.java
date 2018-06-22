package com.test;

import org.springframework.stereotype.Component;


public class TestComponent {
	
	private String str;
	private String portInfo;

	public TestComponent(int port) {
		portInfo = "port : " + port;
	}
	
	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public String getPortInfo() {
		return portInfo;
	}

	public void setPortInfo(String portInfo) {
		this.portInfo = portInfo;
	}
	
}
