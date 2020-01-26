package com.spring5.practice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring5.practice.model.Course;
import com.spring5.practice.service.CourseSercvice;

@Controller
public class CourseController {

	@Autowired
	private CourseSercvice courseService;

	@GetMapping("/course/add")
	public String addCourse_GET(Model model) {
		model.addAttribute("course", new Course());
		model.addAttribute("message", "Please add a course");
		return "course/add";
	}

	@PostMapping("/course/add")
	public String addCourse(Model model, @ModelAttribute(name = "course") Course course) {
		courseService.addCourse(course);
		model.addAttribute("message", "Course added successfully");
		return "redirect:/course/show-all";
	}

	@GetMapping("/course/show-all")
	public String showAll_GET(Model model) {
		model.addAttribute("courses", courseService.getAll());
		model.addAttribute("message", "Showing all courses");
		return "course/show-all";
	}

	@GetMapping("/course/edit")
	public String editCourse_GET(@RequestParam("id") long id, Model model) {
		Course courseObj = courseService.getCourseById(id);
		model.addAttribute("course", courseObj);
		model.addAttribute("message", "Edit the course");
		return "course/edit";
	}

	@PostMapping("/course/edit")
	public String editCourse(Model model, @ModelAttribute(name = "course") Course course) {
		courseService.editCourse(course);
		model.addAttribute("message", "Course edited successfully");
		return "redirect:/course/show-all";
	}

	@GetMapping("/course/search")
	public @ResponseBody String searchCourseByCode(@RequestParam(name = "name") String courseName) {
		var course = courseService.getCourseByName(courseName);
		System.out.println(course.toString());
		return "The course you searched for is: " + course.toString();
	}

}
