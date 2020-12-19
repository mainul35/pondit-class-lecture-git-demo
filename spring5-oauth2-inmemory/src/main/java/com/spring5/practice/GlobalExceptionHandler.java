package com.spring5.practice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR) // 500
	@ExceptionHandler(RuntimeException.class)
	public String handleAnyServerError(HttpServletRequest req, Exception e, Model model) {
		e.printStackTrace();
		model.addAttribute("message", e.getMessage());
		return "error";
	}
}
