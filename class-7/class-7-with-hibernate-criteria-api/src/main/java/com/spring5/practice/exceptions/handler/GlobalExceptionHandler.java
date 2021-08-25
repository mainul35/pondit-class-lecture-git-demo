package com.spring5.practice.exceptions.handler;

import com.spring5.practice.exceptions.ResourceAlreadyExistsException;
import com.spring5.practice.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
	@ResponseStatus(code = HttpStatus.CONFLICT) // 409
	@ExceptionHandler(ResourceAlreadyExistsException.class)
	public String handleConflict(HttpServletRequest req, Exception e, Model model) {
		e.printStackTrace();
		model.addAttribute("message", e.getMessage());
		return "error";
	}

	@ResponseStatus(code = HttpStatus.NOT_FOUND) // 404
	@ExceptionHandler(ResourceNotFoundException.class)
	public String handleNotFound(HttpServletRequest req, Exception e, Model model) {
		model.addAttribute("message", e.getMessage());
		log.error("Requested page was not found");
		e.printStackTrace();
		return "error";
	}

	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR) // 500
	@ExceptionHandler(RuntimeException.class)
	public String handleAnyServerError(HttpServletRequest req, Exception e, Model model) {
		e.printStackTrace();
		model.addAttribute("message", e.getMessage());
		return "error";
	}

//	@ExceptionHandler(BindException.class)
//	public String handleZipNotFoundException(final BindException e, Model model, BindingResult result) {
//		ErrorResponse response = new ErrorResponse("validation_error", "Request Is not valid");
//		List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
//		List<ItemValidationError> validationErrors = new LinkedList<>();
//		fieldErrors.forEach((v) -> {
//			validationErrors.add(new ItemValidationError(v.getObjectName(), v.getField(), v.getRejectedValue(), v.getDefaultMessage()));
//		});
//		response.setErrorItems(validationErrors);
//
//		model.addAttribute("response", response);
//		System.out.println(response.toString());
//		return "student/add";
//	}
}
