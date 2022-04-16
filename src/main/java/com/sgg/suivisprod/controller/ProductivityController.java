package com.sgg.suivisprod.controller;

import static com.sgg.suivisprod.constant.PathConst.PRODUCTIVITY_PATH;
import static com.sgg.suivisprod.constant.PathConst.PRODUCTIVITY_VIEW;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductivityController {

	@GetMapping(PRODUCTIVITY_PATH)
	public String productivity() {
		return PRODUCTIVITY_VIEW;
	}

}
