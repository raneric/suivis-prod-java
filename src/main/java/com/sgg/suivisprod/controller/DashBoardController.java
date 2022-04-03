package com.sgg.suivisprod.controller;

import static com.sgg.suivisprod.utils.AppCont.DASHBOARD_PATH;
import static com.sgg.suivisprod.utils.AppCont.DASHBOARD_VIEW;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashBoardController {
	
	@GetMapping(DASHBOARD_PATH)
	public String dasgBoard() {
		return DASHBOARD_VIEW;
	}
}
