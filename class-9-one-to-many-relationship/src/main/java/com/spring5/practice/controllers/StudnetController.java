package com.spring5.practice.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring5.practice.model.Student;
import com.spring5.practice.service.CountryService;
import com.spring5.practice.service.CourseService;
import com.spring5.practice.service.StudentService;

@Controller
public class StudnetController {
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private CourseService courseService;
	
	@GetMapping("/show-all")
	public String hello(Model model) {
		model.addAttribute("message", "Showing all users");
		studentService.showAll();
		return "index";
	}
	
	@GetMapping("/student/add")
	public String add(Model model) {
		model.addAttribute("student", new Student());
		model.addAttribute("countries", countryService.getAll());
		model.addAttribute("courses", courseService.getAllCourses());
		return "/student/add";
	}
	
	@PostMapping("/student/add")
	public String add(@ModelAttribute("student") Student student, Model model) {
		studentService.save(student);
		return "/student/add";
	}
	
}
