package com.spring5.practice.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_student")
public class Student implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private long id;
	@Column
	private String name;
	@Column
	private int age;
	@OneToMany(cascade = CascadeType.ALL)  
	@JoinColumn(name="id")  
	@OrderColumn(name="type")  
	private Set<Course> courses;
	private String gender;
	private String email;
	private Country country;
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

	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
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
	}

	public Student() {
	}
}
