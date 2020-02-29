package com.springboot.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

    @GetMapping("/")
    public String root(Model model) {
        model.addAttribute("msg", "Spring Boot");
        model.addAttribute("msg2", "Spring Boot is easy");
        return "index";
    }
}
