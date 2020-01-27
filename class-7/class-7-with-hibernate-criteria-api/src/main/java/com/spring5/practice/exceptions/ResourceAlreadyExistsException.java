package com.spring5.practice.exceptions;

public class ResourceAlreadyExistsException extends RuntimeException {
	public ResourceAlreadyExistsException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResourceAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ResourceAlreadyExistsException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
}
