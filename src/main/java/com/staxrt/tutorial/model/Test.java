package com.staxrt.tutorial.model;

import java.io.Serializable;

public class Test implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6355536249026497335L;
	String name;
	Integer age;
	public Test(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}
	public Test() {
		super();
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public Integer getAge() {
		return age;
	}
	
	
}
