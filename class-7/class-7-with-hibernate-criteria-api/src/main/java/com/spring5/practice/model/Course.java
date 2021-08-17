package com.spring5.practice.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "tbl_course")
public class Course implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
//	@GeneratedValue(strategy = GenerationType.TABLE, generator = "table_generator")
//	@TableGenerator(name = "table_course_generator",
//			table = "course_ids",
//			pkColumnName = "seq_id",
//			valueColumnName = "seq_value")
	@Column(name = "id", nullable = false)
	private long courseId;
	@Column(name = "course_name")
	private String courseName;
	@Column(name = "course_code")
	private String courseCode;

	public Course() {
		super();
	}

	public Course(long courseId, String courseName) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseName=" + courseName + "]";
	}

}
