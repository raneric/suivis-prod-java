package com.sgg.suivisprod.controller;

import static com.sgg.suivisprod.utils.AppCont.HISTORY_PATH;
import static com.sgg.suivisprod.utils.AppCont.HISTORY_VIEW;
import static com.sgg.suivisprod.utils.PaginationHelper.ROW_LIMIT;
import static com.sgg.suivisprod.utils.PaginationHelper.PAGINATION_LIMIT;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sgg.suivisprod.domain.Task;
import com.sgg.suivisprod.domain.User;
import com.sgg.suivisprod.repository.TaskRepository;
import com.sgg.suivisprod.repository.UserRepository;
import com.sgg.suivisprod.utils.PaginationHelper;

@Controller
public class HistoryController {

	@Autowired
	TaskRepository taskRepository;

	@Autowired
	UserRepository userRepository;

	@GetMapping(HISTORY_PATH)
	public String history(Model viewModel, Principal userPrinicipal, @RequestParam int currentPage) {
		User user = userRepository.findByUsername(userPrinicipal.getName());

		Pageable   page           = PageRequest.of(currentPage - 1, ROW_LIMIT);
		List<Task> taskListToview = taskRepository.findByTaskType(user.getUserId(), "done", page);
		int        totalTask      = taskRepository.countAllTask(user.getUserId(), "done");

		PaginationHelper pagination = new PaginationHelper(totalTask, currentPage);
		pagination.build();

		int           totalPageNumber = pagination.getTotalPageNumber();
		List<Integer> paginationList  = pagination.getPagination();

		viewModel.addAttribute("tasks", taskListToview);
		viewModel.addAttribute("totalPageNumber", totalPageNumber);
		viewModel.addAttribute("paginationList", paginationList);
		return HISTORY_VIEW;
	}

}
