package com.sgg.suivisprod.controller;

import static com.sgg.suivisprod.utils.AppCont.HISTORY_PATH;
import static com.sgg.suivisprod.utils.AppCont.HISTORY_VIEW;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

	@GetMapping(HISTORY_PATH)
	public String history(Model viewModel, Principal userPrinicipal) {
		User       user    = userRepository.findByUsername(userPrinicipal.getName());
		Pageable   page    = PageRequest.of(0, 2);
		
		List<Task> doneData = taskRepository.findDoneTask(user.getUserId(), page);
		List<Task> progressData = taskRepository.findInProgressTask(user.getUserId(), page);
		List<Task> stdbyData = taskRepository.findStdByTask(user.getUserId(), page);
		List<Task> todoData = taskRepository.findTodoTask(user.getUserId(), page);
		doneData.addAll(progressData);
		doneData.addAll(todoData);
		doneData.addAll(stdbyData);
		viewModel.addAttribute("tasks", doneData);
		return HISTORY_VIEW;
	}

}
