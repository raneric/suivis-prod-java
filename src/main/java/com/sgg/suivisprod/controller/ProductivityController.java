package com.sgg.suivisprod.controller;

import static com.sgg.suivisprod.constant.PathConst.PRODUCTIVITY_PATH;
import static com.sgg.suivisprod.constant.PathConst.PRODUCTIVITY_VIEW;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.sgg.suivisprod.domain.TaskProductivity;
import com.sgg.suivisprod.repository.TaskRepository;
import com.sgg.suivisprod.services.ProductivityService;
import com.sgg.suivisprod.services.TaskService;

@Controller
public class ProductivityController {

	@Autowired
	TaskService taskService;

	@Autowired
	TaskRepository taskRepository;
	
	@Autowired
	ProductivityService productivityService;
	
	@GetMapping(PRODUCTIVITY_PATH)
	public String productivity(Principal userPrincipal) {
		productivityService.calculateTaskProductivity();
		return PRODUCTIVITY_VIEW;
	}

}
