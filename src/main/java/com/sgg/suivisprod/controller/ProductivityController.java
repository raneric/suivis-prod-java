package com.sgg.suivisprod.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static com.sgg.suivisprod.utils.AppCont.PRODUCTIVITY_PATH;
import static com.sgg.suivisprod.utils.AppCont.PRODUCTIVITY_VIEW;

@Controller
public class ProductivityController {

	@GetMapping(PRODUCTIVITY_PATH)
	public String productivity() {
		return PRODUCTIVITY_VIEW;
	}

}
