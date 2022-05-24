package com.sgg.suivisprod.controller;

import static com.sgg.suivisprod.constant.PathConst.HISTORY_PATH;
import static com.sgg.suivisprod.constant.PathConst.HISTORY_VIEW;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.sgg.suivisprod.domain.Task;
import com.sgg.suivisprod.exception.PaginationException;
import com.sgg.suivisprod.services.PaginationService;
import com.sgg.suivisprod.services.TaskService;

@Controller
public class HistoryController {

	@Autowired
	TaskService taskService;

	@Autowired
	PaginationService paginationService;

	@GetMapping(HISTORY_PATH)
	public String history() {
		return HISTORY_VIEW;
	}

	@ModelAttribute("tasks")
	public List<Task> populateTaskList(Principal userPrinicipal, @RequestParam int p) throws PaginationException {
		paginationService.checkPaginationIndex(p);
		return taskService.findByUserAndPage(userPrinicipal.getName(), p);
	}

	@ModelAttribute("totalPageNumber")
	public int populatePagenumber(Principal userPrinicipal) {
		return paginationService.getTotalPageNumber(userPrinicipal.getName());
	}

	@ModelAttribute("paginationList")
	public List<Integer> populatePaginationList(Principal userPrinicipal, @RequestParam(name="p") int currentPage)
			throws PaginationException {
		paginationService.checkPaginationIndex(currentPage);
		return paginationService.buildPaginationList(userPrinicipal.getName(), currentPage);
	}
}
