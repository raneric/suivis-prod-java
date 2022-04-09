package com.sgg.suivisprod.controller;

import static com.sgg.suivisprod.utils.AppCont.HISTORY_PATH;
import static com.sgg.suivisprod.utils.AppCont.HISTORY_VIEW;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mapping.model.Property;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sgg.suivisprod.domain.Task;
import com.sgg.suivisprod.domain.User;
import com.sgg.suivisprod.repository.TaskRepository;
import com.sgg.suivisprod.repository.UserRepository;

@Controller
public class HistoryController {

	@Autowired
	TaskRepository taskRepository;

	@Autowired
	UserRepository userRepository;

	private static final int ROW_PER_PAGE = 8;

	@GetMapping(HISTORY_PATH)
	public String history(Model viewModel, Principal userPrinicipal, @RequestParam int currentPage) {
		User user            = userRepository.findByUsername(userPrinicipal.getName());
		int  totalTask       = taskRepository.countAllTask(user.getUserId());
		int  totalPageNumber = getPageNumber(totalTask);

		Pageable   page                = PageRequest.of(currentPage, ROW_PER_PAGE);
		List<Task> testDataToViewModel = taskRepository.findDoneTask(user.getUserId(), page);

		viewModel.addAttribute("tasks", testDataToViewModel);
		viewModel.addAttribute("totalPageNumber", totalPageNumber);
		return HISTORY_VIEW;
	}

	private int getPageNumber(int totalTaskCount) {
		int pageNumber = (totalTaskCount % ROW_PER_PAGE != 0) ? (totalTaskCount / ROW_PER_PAGE) + 1
				: totalTaskCount / ROW_PER_PAGE;
		return pageNumber;
	}


}
