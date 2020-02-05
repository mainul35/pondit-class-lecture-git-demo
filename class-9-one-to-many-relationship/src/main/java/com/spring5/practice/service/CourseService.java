package com.spring5.practice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring5.practice.config.HibernateConfig;
import com.spring5.practice.exceptions.ResourceAlreadyExistsException;
import com.spring5.practice.exceptions.ResourceNotFoundException;
import com.spring5.practice.model.Course;

@Service
public class CourseService {
	
	private HibernateConfig hibernateConfig;
	
	@Autowired
	public CourseService(HibernateConfig hibernateConfig) {
		
		this.hibernateConfig = hibernateConfig;
		
	
	}
	
	/*private void addCourse(String courseCode, String courseName) {
		var courseObj = new Course();
		courseObj.setId(courses.size() + 1);
		courseObj.setCourseCode(courseCode);
		courseObj.setCourseName(courseName);
		courses.add(courseObj);
	}*/
	
	public void addCourse(Course course) {	
		var session = hibernateConfig.getSession();
		var transection = session.beginTransaction();
		checkCourseInDb(course);
		course.setCourseCode(course.getCourseName().substring(0, 2));
		session.save(course);
		transection.commit();
	}
	
	
	private void checkCourseInDb(Course c) {
		
		var session = hibernateConfig.getSession();
		Transaction tx = session.getTransaction();
		if (!tx.isActive())
			tx = session.beginTransaction();
		var query = session.getEntityManagerFactory().createEntityManager().createQuery("select c from Course c where courseCode=:courseCode ",Course.class);
		query.setParameter("courseCode", c.getCourseCode());
		
		if(query.getResultStream().findAny().isPresent()) {
		
			tx.rollback();
			session.close();
			throw new ResourceAlreadyExistsException("Course Already exists");
		}
	}
	

	
	public void saveEditedCourse (Course c) {
		
		var session = hibernateConfig.getSession();
		Transaction tx = session.getTransaction();
		if (!tx.isActive())
			tx = session.beginTransaction();

		session.update(c);		
		tx.commit();
		session.close();

	}
	
	/*public void deleteCourseByCourseCode(String courseCode) {
		for(int i = 0; i< courses.size(); i++) {
			if(courses.get(i).getCourseCode().equals(courseCode)) {
				courses.remove(i);
				break;
			}
		}
	}*/
	
	public Course getCourseByCourseCode(String courseCode) {
		
		var session = hibernateConfig.getSession();
		Transaction tx = session.getTransaction();
		if (!tx.isActive())
			tx = session.beginTransaction();
		
		var query = session.getEntityManagerFactory().createEntityManager().createQuery("select c from Course c where courseCode=:courseCode ",Course.class);
		query.setParameter("courseCode", courseCode);
		Course course =  query.getResultList().get(0);
		return course;
	}

	
	public List<Course> getAllCourses(){
		
		var session = hibernateConfig.getSession();
		Transaction tx = session.getTransaction();
		if (!tx.isActive())
			tx = session.beginTransaction();
		
  		var query = session.getEntityManagerFactory().createEntityManager().createQuery("select c from Course c ", Course.class);
		var courseList =  query.getResultList();
		
		return courseList;
		/*var courseList = new ArrayList<Course>();
		
		courseList.add(new Course(1,"CSE111" ,"P L 1"));
		courseList.add(new Course(2,"CSE112" ,"P L 2"));
		
		return courseList;*/
		
	}
}
