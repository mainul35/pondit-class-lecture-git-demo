package com.spring5.practice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.spring5.practice.model.Country;

@Controller
@RequestMapping("/country")
public class CountryController {

//	@Autowired
//	private CountryService countryService;

	@GetMapping("/add")
	public String addCountry_GET(Model model) {
		model.addAttribute("pageTitle", "Add Country");
		model.addAttribute("country", new Country());
		model.addAttribute("message", "Please add a country");
		return "country/add";
	}

	@PostMapping("/add")
	public String addCountry(Model model, @ModelAttribute(name = "country") Country country) {
//		countryService.addCountry(country);
		model.addAttribute("message", "Country added successfully");
		return "redirect:/country/show-all";
	}

	@GetMapping("/show-all")
	public String showAll_GET(Model model) {
		model.addAttribute("pageTitle", "Country List");
//		model.addAttribute("countries", countryService.getAll());
		model.addAttribute("message", "Showing all countries");
		return "country/show-all";
	}

	@GetMapping("/show/{code}")
	public String showAll_GET(@PathVariable(name = "code") String code, Model model) {
//		model.addAttribute("country", countryService.getCountryByCode(code));
		model.addAttribute("message", "Showing country with code");
		return "country/show-by-code";
	}

}
