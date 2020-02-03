package com.spring5.practice.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring5.practice.config.HibernateConfig;
import com.spring5.practice.model.Course;
import com.spring5.practice.model.Student;

@Service
public class StudentService {

	@Autowired
	private CountryService countryService;
	@Autowired
	private CourseService courseService;
	
	private final HibernateConfig hibernateConfig;
	
	public StudentService(HibernateConfig hibernateConfig) {
		this.hibernateConfig = hibernateConfig;
	}
	
	public void showAll() {
	}

	@Transactional
	public void save(Student student) {
		// TODO Auto-generated method stub
		var session = hibernateConfig.getSession();
		Transaction tx = session.getTransaction();
		if (!tx.isActive())
			tx = session.beginTransaction();
		var country = countryService.getCountryByCode(student.getCountryCode());
		student.setCountry(country);
		Set<Course> courses = new HashSet<Course>();
		for(var courseCode: student.getCourseCodes()) {
			var course = courseService.getCourseByCourseCode(courseCode);
			courses.add(course);
		}
		student.setCourses(courses);
		session.save(student);
		tx.commit();
	}
}
