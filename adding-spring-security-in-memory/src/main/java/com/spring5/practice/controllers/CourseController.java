package com.spring5.practice.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.spring5.practice.model.Course;
import com.spring5.practice.service.CourseService;

@Controller
@RequestMapping("/course")
public class CourseController {

	@Autowired
	CourseService courseService;

	@GetMapping("/add")
	public String getAddCoursePage(Model model) {
		model.addAttribute("pageTitle", "Add Course");
		model.addAttribute("course", new Course());
		model.addAttribute("message", "Add a new Course");

		return "course/add";

	}

	@PostMapping("/add")
	public String addCourse(Model model, @ModelAttribute(name = "course") Course course) {
		courseService.addCourse(course);
		model.addAttribute("message", "Course added successfully");

		return "redirect:/course/show-all";

	}

	@GetMapping("/show-all")
	public String showAllCourse(Model model) {
		model.addAttribute("pageTitle", "Course List");
		model.addAttribute("courses", courseService.getAllCourses());
		model.addAttribute("message", "Showing all course...");

		return "/course/show-all";
	}

	@GetMapping("/courses")
	public String coursesPage(Model model) {

		model.addAttribute("course_list", courseService.getAllCourses());
		model.addAttribute("course", new Course());
		model.addAttribute("message", "Showing all course...");

		return "course/courses";

	}

	@GetMapping("/edit")
	public String editCourseByCourseCode(Model model, @RequestParam("courseCode") String courseCode) {

		model.addAttribute("course", courseService.getCourseByCourseCode(courseCode));
		// model.addAttribute("course", new Course());

		return "course/edit";
	}

	@PostMapping("/edit")
	public String saveEditedCourse(Model model, @ModelAttribute(name = "course") Course course) {
		courseService.saveEditedCourse(course);
		model.addAttribute("message", "Course saved successfully");

		return "redirect:/course/courses";
	}

	@GetMapping("/delete")
	public String deleteCourseByCourseCode(Model model, @RequestParam("courseCode") String courseCode) {

//		 courseService.deleteCourseByCourseCode(courseCode);
		model.addAttribute("message", "Course deleted successfully");

		return "redirect:/course/courses";
	}

	@PostMapping("/search")
	public String searchCourseByCourseCode(Model model, @ModelAttribute(name = "course") Course course) {

		var courseList = new ArrayList();
		courseList.add(courseService.getCourseByCourseCode(course.getCourseCode()));
		model.addAttribute("course_list", courseList);

		return "course/courses";
	}

}
