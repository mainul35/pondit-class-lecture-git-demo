package com.spring5.practice.repositories;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring5.practice.model.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CourseRepository extends JpaRepository<Course, Long>{
	
	Course findByCourseId(Long id);
	
	Course findByCourseCode(String courseCode);

	@Query(value = "select *" +
			" from tbl_course c" +
			" where c.course_name like %:keyword% " +
			"or c.course_code like %:keyword%", nativeQuery = true)
	List<Course> getCoursesByQueryString(@Param("keyword") String query);
	
//	List<Student> findByCountry_CountryCode(String countryCode);
}
