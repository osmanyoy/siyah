package com.spring.training.property;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix="my.turkcell")
@Validated
public class MyPropReadObject {
	
	@Size(min=5)
	private String project;
	
	@Max(65000)
	@Min(1024)
	private int port;
	
	private String[] arr;
	
	private List<String> arrList;
	
	private Map<String,Integer> typeMap;
	
	private MyExtraProp myExtraProp;

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String[] getArr() {
		return arr;
	}

	public void setArr(String[] arr) {
		this.arr = arr;
	}

	public List<String> getArrList() {
		return arrList;
	}

	public void setArrList(List<String> arrList) {
		this.arrList = arrList;
	}

	public Map<String, Integer> getTypeMap() {
		return typeMap;
	}

	public void setTypeMap(Map<String, Integer> typeMap) {
		this.typeMap = typeMap;
	}

	@Override
	public String toString() {
		return "MyPropReadObject [project="
		       + project
		       + ", port="
		       + port
		       + ", arr="
		       + Arrays.toString(arr)
		       + ", arrList="
		       + arrList
		       + ", typeMap="
		       + typeMap
		       + "]";
	}

	public MyExtraProp getMyExtraProp() {
		return myExtraProp;
	}

	public void setMyExtraProp(MyExtraProp myExtraProp) {
		this.myExtraProp = myExtraProp;
	}
	
	
	
}
