package com.sgg.suivisprod.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import static com.sgg.suivisprod.constant.PathConst.ERROR_VIEW;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(PaginationException.class)
	public ModelAndView handlePageValueException(PaginationException paginationException) {
		return new ModelAndView(ERROR_VIEW, "error", paginationException.getMessage());
	}

}
