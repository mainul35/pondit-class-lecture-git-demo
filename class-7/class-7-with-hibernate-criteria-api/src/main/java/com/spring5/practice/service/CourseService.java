package com.spring5.practice.service;

import com.spring5.practice.model.Course;
import org.hibernate.NonUniqueResultException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService extends BaseService<Course> {

	public CourseService() {
		super(new Course());
	}

	public void addCourse(Course course) {
		checkCourseInDb(course);
		course.setCourseCode(course.getCourseName().substring(0, 2));
		save(course);
	}

	private void checkCourseInDb(Course c) {

		var cb = getCriteriaBuilder();
		var cq = cb.createQuery(Course.class);
		var root = cq.from(Course.class);

		cq.where(cb.equal(root.get("courseCode"), c.getCourseCode()));
		var result = query(cq).getResultList();
		if (result.size() > 0) {
			throw new NonUniqueResultException(result.size());
		}
	}

	public void saveEditedCourse(Course c) {
		var cb = getCriteriaBuilder();
		var cq = cb.createQuery(Course.class);
		var root = cq.from(Course.class);

		cq.where(cb.equal(root.get("courseId"), c.getCourseId()));
		var result = query(cq).getResultList();
		if (result.size() > 1) {
			throw new NonUniqueResultException(result.size());
		} else {
			Course course = result.get(0);
			BeanUtils.copyProperties(c, course);
			save(course);
		}
	}

	public Course getCourseByCourseCode(String courseCode) {

		var cb = getCriteriaBuilder();
		var cq = cb.createQuery(Course.class);
		var root = cq.from(Course.class);

		cq.where(cb.equal(root.get("courseCode"), courseCode));
		var course = (Course) query(cq).getResultList().get(0);
		return course;
	}

	public List<Course> getAllCourses() {

		var cb = getCriteriaBuilder();
		var cq = cb.createQuery(Course.class);
		var root = cq.from(Course.class);
		var result = query(cq).getResultList();
		return result;
	}
}
