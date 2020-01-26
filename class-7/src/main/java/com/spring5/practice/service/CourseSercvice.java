package com.spring5.practice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.spring5.practice.exceptions.ResourceAlreadyExistsException;
import com.spring5.practice.exceptions.ResourceNotFoundException;
import com.spring5.practice.model.Course;

@Service
public class CourseSercvice {
//	Course er jonne course 
//	create, edit, show all, getByCourseCode 
//	ei koy ta thaklei hobe

	private static List<Course> courses = new ArrayList<Course>();

	private static final String[] COURSES = { "ENG-1", "MATH-1", "PHY-1", "CHEM-1" };

	public CourseSercvice() {
		Stream.of(COURSES).forEach(course -> {
			addCourse(course);
		});
	}

	private void addCourse(String courseName) {
		var courseObj = new Course();
		courseObj.setCourseId(courses.size() + 1);
		courseObj.setCourseName(courseName);
		courses.add(courseObj);
	}

	//
	//

	public void addCourse(Course course) {
		checkCourseInList(course);
		course.setCourseId(courses.size() + 1);
		courses.add(course);
	}

	private void checkCourseInList(Course c) {
		if (courses.stream().filter(course -> course.getCourseName().equals(c.getCourseName())).findAny().isPresent()) {
			throw new ResourceAlreadyExistsException("Course already exists in list");
		}
	}

	public Course getCourseByName(String courseName) {

		return courses.stream().filter(course -> course.getCourseName().equals(courseName)).findAny()
				.orElseThrow(() -> new ResourceNotFoundException("Course not found with this name"));
	}

	public List<Course> getAll() {
		return courses;
	}

	//
	//

	public Course getCourseById(long courseId) {
		return courses.stream().filter(course -> course.getCourseId() == courseId).findFirst()
				.orElseThrow(() -> new ResourceNotFoundException("Course not found with this id"));
	}

	public void editCourse(Course c) {
		Course courseObj = getCourseById(c.getCourseId());
		courseObj.setCourseName(c.getCourseName());
	}
}
