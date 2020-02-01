package com.spring5.practice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.spring5.practice.exceptions.ResourceAlreadyExistsException;
import com.spring5.practice.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ResponseStatus(code = HttpStatus.CONFLICT) // 409
	@ExceptionHandler(ResourceAlreadyExistsException.class)
	public String handleConflict(HttpServletRequest req, Exception e, Model model) {

		model.addAttribute("message", e.getMessage());
		return "error";
	}

	@ResponseStatus(code = HttpStatus.NOT_FOUND) // 404
	@ExceptionHandler(ResourceNotFoundException.class)
	public String handleNotFound(HttpServletRequest req, Exception e, Model model) {

		model.addAttribute("message", e.getMessage());
		return "error";
	}

	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR) // 500
	@ExceptionHandler(RuntimeException.class)
	public String handleAnyServerError(HttpServletRequest req, Exception e, Model model) {

		model.addAttribute("message", e.getMessage());
		return "error";
	}
}
