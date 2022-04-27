package com.sgg.suivisprod.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;

public class PaginationException extends Exception {
	public PaginationException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
}
