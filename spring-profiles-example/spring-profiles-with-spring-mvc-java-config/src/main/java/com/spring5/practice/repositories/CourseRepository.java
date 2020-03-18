package com.spring5.practice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring5.practice.model.Course;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CourseRepository extends JpaRepository<Course, Long>{
	
	Course findByCourseId(Long id);
	
	Course findByCourseCode(String courseCode);
	
//	List<Student> findByCountry_CountryCode(String countryCode);
}
