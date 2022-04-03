package com.sgg.suivisprod.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.sgg.suivisprod.utils.AppCont.TASK_VIEW;
import static com.sgg.suivisprod.utils.AppCont.TASK_PATH;
import static com.sgg.suivisprod.utils.AppCont.NEW_TASK_PATH;

@Controller
@RequestMapping(TASK_PATH)
public class NewTaskController {

	@GetMapping(NEW_TASK_PATH)
	public String newTask() {
		return TASK_VIEW;
	}

}
