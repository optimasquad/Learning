package com.bfm.analyzer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class JsonTest {

	@JsonInclude(Include.NON_NULL)
	private JsonTest1 JsonTest1;

	@JsonInclude(Include.NON_NULL)
	private String salary;

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public JsonTest1 getJsonTest1() {
		return JsonTest1;
	}

	public void setJsonTest1(JsonTest1 jsonTest1) {
		JsonTest1 = jsonTest1;
	}

}
