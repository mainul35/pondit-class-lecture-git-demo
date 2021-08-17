package com.spring5.practice.controllers;

import java.util.HashMap;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring5.practice.dtos.StudentDto;
import com.spring5.practice.request.Student;
import com.spring5.practice.service.CountryService;
import com.spring5.practice.service.CourseService;
import com.spring5.practice.service.StudentService;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StudnetController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private CountryService countryService;

	@Autowired
	private CourseService courseService;

	@GetMapping("/student/show-all")
	public String hello(Model model) {
		model.addAttribute("message", "Showing all users");
		model.addAttribute("pageTitle", "Student List");
		model.addAttribute("students", studentService.showAll());
		studentService.showAll().forEach(s -> {
			System.out.println(s.toString());
		});
		return "student/show-all";
	}

	@GetMapping("/student/add")
	public String add(Model model) {
		model.addAttribute("pageTitle", "Add Student");
		model.addAttribute("student", new Student());
		var genders = new HashMap<String, String>();
		genders.put("M", "Male");
		genders.put("F", "Female");
		model.addAttribute("genders", genders);
		model.addAttribute("countries", countryService.getAll());
		model.addAttribute("courses", courseService.getAllCourses());
		return "student/add";
	}

	@PostMapping("/student/add")
	public String add(@ModelAttribute("student") Student student, Model model) {

		var studentDto = new StudentDto();
		BeanUtils.copyProperties(student, studentDto);
		studentService.save(studentDto);
		studentService.showAll().forEach(s -> {
			System.out.println(s.toString());
		});
		return "redirect:/student/show-all";
	}

	@GetMapping("/student/edit")
	public String edit(Model model, @RequestParam("id") String id) {
		model.addAttribute("pageTitle", "Edit Student");
		var student = studentService.getById(id);
		model.addAttribute("student", student);
		var genders = new HashMap<String, String>();
		genders.put("M", "Male");
		genders.put("F", "Female");
		model.addAttribute("studentId",id);
		model.addAttribute("genders", genders);
		model.addAttribute("countries", countryService.getAll());
		model.addAttribute("courses", courseService.getAllCourses());
		return "student/edit";
	}

	@PostMapping("/student/update")
	public String update(Model model,@ModelAttribute("student") Student student, @RequestParam("id") long id){
//		var studentEntity = studentService.getById(id+"");
		var studentDto = new StudentDto();
		BeanUtils.copyProperties(student, studentDto);
		studentService.update(studentDto, id);
		studentService.showAll().forEach(s -> {
			System.out.println(s.toString());
		});
//
//		System.out.println("-----------------------------------");
//		System.out.println("student object received ");
//		System.out.println(student);
//		System.out.println("-----------------------------------");
//		System.out.println("-----------------------------------");
//		System.out.println("student object findbyid ");
//		System.out.println(studentEntity);
//		System.out.println("-----------------------------------");
		return "redirect:/student/show-all";
	}
}
