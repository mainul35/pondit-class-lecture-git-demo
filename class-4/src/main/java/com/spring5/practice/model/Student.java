package com.spring5.practice.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class Student implements Serializable{

	private long id;
	private String name;
	private int age;
	private Set<Course> courses;
	private String gender;
	private String email;
	private List<Country> country;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
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
	public Set<Course> getCourses() {
		return courses;
	}
	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Country> getCountry() {
		return country;
	}
	public void setCountry(List<Country> country) {
		this.country = country;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", age=" + age + ", courses=" + courses + ", gender=" + gender
				+ ", email=" + email + ", country=" + country + "]";
	}
	public Student(long id, String name, int age, Set<Course> courses, String gender, String email,
			List<Country> country) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.courses = courses;
		this.gender = gender;
		this.email = email;
		this.country = country;
	}

	public Student() {
	}
}
