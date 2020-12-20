package com.example.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleVerificationFailureException(ResourceNotFoundException e,
                                                     HttpServletRequest request,
                                                     HttpServletResponse response, Model model) {
//        ModelAndView mav = new ModelAndView("errors/404");
        model.addAttribute("error_message", e.getMessage());
        return "errors/404";
    }
}
