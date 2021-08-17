package com.spring5.practice.service;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

//import org.springframework.transaction.annotation.Transactional;

import org.hibernate.Transaction;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring5.practice.config.HibernateConfig;
import com.spring5.practice.dtos.StudentDto;
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

	public List<Student> showAll() {
		var cb = hibernateConfig.getCriteriaBuilder();
		var cq = cb.createQuery(Student.class);
		var root = cq.from(Student.class);
		cq.select(root);
		return hibernateConfig.getSession()
				.getEntityManagerFactory()
				.createEntityManager()
				.createQuery(cq)
				.getResultList();
	}

	@Transactional
	public void save(StudentDto studentDto) {
		// TODO Auto-generated method stub

		var country = countryService.getCountryByCode(studentDto.getCountryCode());
		var studentEntity = new Student();
		BeanUtils.copyProperties(studentDto, studentEntity); // (from,to)
		studentEntity.setCountry(country);
		Set<Course> courses = new HashSet<Course>();
		if (studentDto.getCourseCodes() != null) {
			for (var courseCode : studentDto.getCourseCodes()) {
				var course = courseService.getCourseByCourseCode(courseCode);
				courses.add(course);
			}
		}
		studentEntity.setCourses(courses);
		System.out.println(studentEntity+" ----------------- inside add() of student Service---------------");
		var session = hibernateConfig.getSession();
		Transaction tx = session.getTransaction();
		if (!tx.isActive())
			tx = session.beginTransaction();
		session.save(studentEntity);
		session.flush();
		tx.commit();
	}

	public Student getById(String id) {
		var cb = hibernateConfig.getCriteriaBuilder();
		var cq = cb.createQuery(Student.class);
		var root = cq.from(Student.class);
		cq.select(root);
		cq.where(cb.equal(root.get("id"), id));
		return hibernateConfig.query(cq).getSingleResult();
	}

	@Transactional
	public void update(StudentDto studentDto, long id) {
		// TODO Auto-generated method stub

	// -----------------------------update with hibernate session -----------------------
		var country = countryService.getCountryByCode(studentDto.getCountryCode());
		var studentEntity = getById(id+"");
		BeanUtils.copyProperties(studentDto, studentEntity); // (from,to)
		studentEntity.setCountry(country);
		Set<Course> courses = new HashSet<Course>();
		if (studentDto.getCourseCodes() != null) {
			for (var courseCode : studentDto.getCourseCodes()) {
				var course = courseService.getCourseByCourseCode(courseCode);
				courses.add(course);
			}
		}
		studentEntity.setCourses(courses);
		System.out.println(studentEntity+" ----------------- inside update() of student Service---------------");
		// performing update
		var session = hibernateConfig.getSession();
		Transaction tx = session.getTransaction();
		if (!tx.isActive())
			tx = session.beginTransaction();
		session.update(studentEntity);
		session.flush();
		tx.commit();
		// ----------------------------- end of update with hibernate session -----------------------

		// ----------------------------update through criteria---------------------
	/*	var criteriaBuilder = hibernateConfig.getCriteriaBuilder();
		var criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Student.class);
		var root = criteriaUpdate.from(Student.class);
		criteriaUpdate.set("name", studentEntity.getName());
		criteriaUpdate.set("age", studentEntity.getAge());
		criteriaUpdate.set("email", studentEntity.getEmail());
		criteriaUpdate.set("gender", studentEntity.getGender());
		criteriaUpdate.set("country", studentEntity.getCountry());
//		criteriaUpdate.set("courses", studentEntity.getCourses());
		criteriaUpdate.where(criteriaBuilder.equal(root.get("id"), id));
		// performing update
		var session = hibernateConfig.getSession();
		Transaction tx = session.getTransaction();
		if (!tx.isActive())
			tx = session.beginTransaction();
		session.createQuery(criteriaUpdate).executeUpdate();
		session.flush();
		tx.commit(); */

		//----------- perform update----------
//		hibernateConfig.getSession()
//				.getEntityManagerFactory()
//				.createEntityManager()
//				.createQuery(criteriaUpdate)
//				.executeUpdate();
//		session.flush();
//		tx.commit();
		// --------------------------end of update with criteria------------------------------

		// -----------------------------update with hql---------------------------
//		String hqlUpdate = "update tbl_student c set c.name = :newName, c.age = :newAge, c.email = :newEmail," +
//				"c.mobile = :newMobile, c.gender = :newGender, c.country = :newCountry where c.id = :oldId";
//		// or String hqlUpdate = "update Customer set name = :newName where name = :oldName";
//		int updatedEntities = session.createQuery( hqlUpdate )
//				.setString( "newName", studentEntity.getName() )
//				.setString( "oldId", studentEntity.getId() )
//				.executeUpdate();
		// --------------------------------end of update with hql----------------------------

	}
}
