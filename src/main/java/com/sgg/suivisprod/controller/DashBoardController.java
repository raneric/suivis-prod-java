package com.sgg.suivisprod.controller;

import static com.sgg.suivisprod.constant.PathConst.DASHBOARD_PATH;
import static com.sgg.suivisprod.constant.PathConst.DASHBOARD_VIEW;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.sgg.suivisprod.domain.Task;
import com.sgg.suivisprod.services.TaskService;

@Controller
public class DashBoardController {

	@Autowired
	TaskService taskService;

	@GetMapping(DASHBOARD_PATH)
	public String dasgBoard() {
		return DASHBOARD_VIEW;
	}

	@ModelAttribute("allTask")
	private Map<String, List<Task>> pupulate(Principal principal) {
		return taskService.findTaskGroupedByState(principal.getName());
	}
}
