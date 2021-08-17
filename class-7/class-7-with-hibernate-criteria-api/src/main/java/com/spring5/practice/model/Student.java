package com.spring5.practice.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "tbl_student")
public class Student implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
//	@GeneratedValue(strategy = GenerationType.TABLE, generator = "table_generator")
//	@TableGenerator(name = "table_student_generator",
//			table = "student_ids",
//			pkColumnName = "seq_id",
//			valueColumnName = "seq_value")
	@Column(name = "id")
	private long id;
	@Column
	private String name;
	@Column
	private int age;
//	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Course.class)
	@ManyToMany( cascade = CascadeType.ALL, targetEntity = Course.class)
	@JoinTable(name = "student_courses", joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"))
	private Set<Course> courses;
	@Column(name = "gender")
	private String gender;
	@Column(name = "email")
	private String email;
	@ManyToOne
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

	public Student() {
	}
}
