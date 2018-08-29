package com.bfm.analyzer;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "user")
public class JsonTest1 {

	private String name;
	private String age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

}
