package com.spring5.practice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

// For showing index.jsp instead of showing the "not found page" error
@Controller
public class RootController {
	@GetMapping("/")
	public String root() {
		return "index";
	}
}
