package com.sgg.suivisprod.controller;

import static com.sgg.suivisprod.constant.PathConst.HISTORY_PATH;
import static com.sgg.suivisprod.constant.PathConst.HISTORY_VIEW;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.sgg.suivisprod.domain.Task;
import com.sgg.suivisprod.domain.User;
import com.sgg.suivisprod.repository.TaskRepository;
import com.sgg.suivisprod.repository.UserRepository;
import com.sgg.suivisprod.services.PaginationService;
import com.sgg.suivisprod.services.TaskService;

@Controller
public class HistoryController {

	@Autowired
	TaskRepository taskRepository;

	@Autowired
	TaskService taskService;

	@Autowired
	PaginationService paginationService;

	@GetMapping(HISTORY_PATH)
	public String history(Model viewModel, Principal userPrinicipal, @RequestParam int currentPage) {
		return HISTORY_VIEW;
	}

	@ModelAttribute("tasks")
	public List<Task> populateTaskList(Principal userPrinicipal, @RequestParam int currentPage) {
		return taskService.findByUserAndPage(userPrinicipal.getName(), currentPage);
	}

	@ModelAttribute("totalPageNumber")
	public int populatePagenumber(Principal userPrinicipal) {
		return paginationService.getTotalPageNumber(userPrinicipal.getName());
	}

	@ModelAttribute("paginationList")
	public List<Integer> populatePaginationList(Principal userPrinicipal, @RequestParam int currentPage) {
		return paginationService.buildPaginationList(userPrinicipal.getName(), currentPage);
	}
}
