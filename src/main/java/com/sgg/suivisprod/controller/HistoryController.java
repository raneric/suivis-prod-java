package com.sgg.suivisprod.controller;

import static com.sgg.suivisprod.utils.AppCont.HISTORY_PATH;
import static com.sgg.suivisprod.utils.AppCont.HISTORY_VIEW;

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

@Controller
public class HistoryController {

	@Autowired
	TaskRepository taskRepository;

	@Autowired
	UserRepository userRepository;

	private static final int ROW_LIMIT        = 8;
	private static final int PAGINATION_LIMIT = 5;

	@GetMapping(HISTORY_PATH)
	public String history(Model viewModel, Principal userPrinicipal, @RequestParam int currentPage) {
		User user               = userRepository.findByUsername(userPrinicipal.getName());
		int  zeroBasedPageIndex = currentPage - 1;

		Pageable   page           = PageRequest.of(zeroBasedPageIndex, ROW_LIMIT);
		List<Task> taskListToview = taskRepository.findByTaskType(user.getUserId(), "done", page);

		int           totalPageNumber = getPageNumber(taskRepository.countAllTask(user.getUserId(), "done"));
		List<Integer> paginationList  = buildPaginationList(zeroBasedPageIndex, totalPageNumber);

		viewModel.addAttribute("tasks", taskListToview);
		viewModel.addAttribute("totalPageNumber", totalPageNumber);
		viewModel.addAttribute("paginationList", paginationList);
		return HISTORY_VIEW;
	}

	/**
	 * 
	 * @param totalTaskCount
	 * @return int of total page number
	 */
	private int getPageNumber(int totalTaskCount) {
		return totalTaskCount / ROW_LIMIT;
	}

	/**
	 * Build pagination list for history view pagination
	 * 
	 * @param currentPage
	 * @param totalPageNumber
	 * @return list of Integer
	 */
	private List<Integer> buildPaginationList(int currentPage, int totalPageNumber) {
		List<Integer> pageList   = new ArrayList<>();
		int           currentRow = currentPage == 0 ? 1 : currentPage;

		if (currentRow > totalPageNumber - PAGINATION_LIMIT) {
			currentRow = totalPageNumber - PAGINATION_LIMIT + 1;
		}

		for (int i = 1; i <= PAGINATION_LIMIT; i++) {
			pageList.add(currentRow);

			if (currentRow == totalPageNumber) {
				break;
			}

			currentRow++;
		}

		return pageList;
	}

}
