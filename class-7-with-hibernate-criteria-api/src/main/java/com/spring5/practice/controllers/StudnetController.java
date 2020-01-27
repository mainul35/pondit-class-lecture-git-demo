package com.spring5.practice.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring5.practice.model.Student;
import com.spring5.practice.service.StudentService;

@Controller
public class StudnetController {
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/show-all")
	public String hello(Model model) {
		model.addAttribute("message", "Showing all users");
		studentService.showAll();
		return "index";
	}
	
	@GetMapping("/add-user")
	public String add(@RequestParam("name") String name, Model model) {
		
		studentService.createUser(name);
		model.addAttribute("message", "Student created successfully");
		return "index";
	}
	
	@GetMapping("/remove-user")
	public String remove(@RequestParam("name") String name, Model model) {
		studentService.removeUserByName(name);
		model.addAttribute("message", "Student removed successfully");
		return "index";
	}
}
