package com.spring5.practice.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spring5.practice.model.Student;

@Service
public class StudentService {

	private List<Student> students;
	
	public StudentService(List<Student> students) {
		this.students = students;
	}

	public Student createUser(String name) {
		var user = new Student();
		user.setName(name);
		students.add(user);
		return user;
	}
	
	public void removeUserByName(String name) {
		Iterator<Student> iterator = students.iterator();
		while (iterator.hasNext()) {
			if (iterator.next().getName().equals(name)) {
				iterator.remove();
			}
		}
	}
	
	public void showAll() {
		students.forEach(System.out::println);
	}
}
