package com.sgg.suivisprod.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static com.sgg.suivisprod.utils.AppCont.HISTORY_VIEW;
import static com.sgg.suivisprod.utils.AppCont.HISTORY_PATH;
@Controller
public class HistoryController {
	
	@GetMapping(HISTORY_PATH)
	public String history() {
		return HISTORY_VIEW;
	}
	
}
