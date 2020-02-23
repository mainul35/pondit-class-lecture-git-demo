package com.spring5.practice.controllers;

import com.spring5.practice.dtos.CourseDto;
import com.spring5.practice.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CourseRestController {

    private final CourseService courseService;

    public CourseRestController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping(value = "/course/search")
    public ResponseEntity<?> searchCourseByCourseCode(@RequestParam(name = "query") String query) {
        var data = courseService.searchCourse(query);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
