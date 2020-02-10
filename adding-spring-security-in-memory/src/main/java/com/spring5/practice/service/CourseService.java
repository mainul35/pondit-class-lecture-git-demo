package com.spring5.practice.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring5.practice.exceptions.ResourceAlreadyExistsException;
import com.spring5.practice.model.Course;
import com.spring5.practice.repositories.CourseRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CourseService {

	@Autowired
	private CourseRepository courseRepository;

	public void addCourse(Course course) {
		checkCourseInDb(course);
		course.setCourseCode(course.getCourseName().substring(0, 2));
		courseRepository.save(course);
	}

	private void checkCourseInDb(Course c) {
		var course = courseRepository.findByCourseCode(c.getCourseCode());
		if (course != null) {
			throw new ResourceAlreadyExistsException("Course Already exists");
		}
	}

	public void saveEditedCourse(Course c) {

		var course = courseRepository.findByCourseId(c.getCourseId());
		BeanUtils.copyProperties(c, course);
		course.setCourseCode(course.getCourseName().substring(0, 2));
		courseRepository.save(course);

	}

	public Course getCourseByCourseCode(String courseCode) {
		var course = courseRepository.findByCourseCode(courseCode);
		return course;
	}

	public List<Course> getAllCourses() {
		return courseRepository.findAll();
	}
}
