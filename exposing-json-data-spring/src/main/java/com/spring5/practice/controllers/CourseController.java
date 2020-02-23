package com.spring5.practice.controllers;

import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.spring5.practice.model.Course;
import com.spring5.practice.service.CourseService;

@Controller
public class CourseController {

	private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }


    @GetMapping("/course/add")
	public String getAddCoursePage(Model model) {
		model.addAttribute("pageTitle", "Add Course");
		model.addAttribute("course", new Course());
		model.addAttribute("message", "Add a new Course");

		return "course/add";

	}

	@PostMapping("/course/add")
	public String addCourse(Model model, @ModelAttribute(name = "course") Course course) {
		courseService.addCourse(course);
		model.addAttribute("message", "Course added successfully");

		return "redirect:/course/show-all";

	}

	@GetMapping("/course/show-all")
	public String showAllCourse(Model model) {
		model.addAttribute("pageTitle", "Course List");
		model.addAttribute("courses", courseService.getAllCourses());
		model.addAttribute("message", "Showing all course...");

		return "/course/show-all";
	}

	@GetMapping("/course/courses")
	public String coursesPage(Model model) {

		model.addAttribute("course_list", courseService.getAllCourses());
		model.addAttribute("course", new Course());
		model.addAttribute("message", "Showing all course...");

		return "course/courses";

	}

	@GetMapping("/course/edit")
	public String editCourseByCourseCode(Model model, @RequestParam("courseCode") String courseCode) {

		model.addAttribute("course", courseService.getCourseByCourseCode(courseCode));
		// model.addAttribute("course", new Course());

		return "course/edit";
	}

	@PostMapping("/course/edit")
	public String saveEditedCourse(Model model, @ModelAttribute(name = "course") Course course) {
		courseService.saveEditedCourse(course);
		model.addAttribute("message", "Course saved successfully");

		return "redirect:/course/courses";
	}

	@GetMapping("/course/delete")
	public String deleteCourseByCourseCode(Model model, @RequestParam("courseCode") String courseCode) {

//		 courseService.deleteCourseByCourseCode(courseCode);
		model.addAttribute("message", "Course deleted successfully");

		return "redirect:/course/courses";
	}

	@GetMapping(value = "/search-page")
	public String searchCourseView(Model model) {
		return "/course/search";
	}

	@GetMapping(value = "/course/search")
	public @ResponseBody
	ResponseEntity<?> searchCourseByCourseCode(@RequestParam(name = "query") String query) {
	    var data = courseService.searchCourse(query);
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

}
