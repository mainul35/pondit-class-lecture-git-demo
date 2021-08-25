package com.spring5.practice.controllers;

import com.spring5.practice.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// For showing index.jsp instead of showing the "not found page" error
@Controller
public class RootController {
	@GetMapping("/")
	public String root() {
		throw new ResourceNotFoundException("Requested resource was not found");
	}
}
