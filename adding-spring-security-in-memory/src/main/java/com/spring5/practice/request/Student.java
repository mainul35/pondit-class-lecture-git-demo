package com.spring5.practice.request;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student implements Serializable{
	private String name;
	private int age;
	private String email;
	private String countryCode;
	private List<String> courseCodes;
	private String gender;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public List<String> getCourseCodes() {
		return courseCodes;
	}

	public void setCourseCodes(List<String> courseCodes) {
		this.courseCodes = courseCodes;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + ", email=" + email + ", countryCode=" + countryCode
				+ ", courseCodes=" + courseCodes + ", gender=" + gender + "]";
	}
}
