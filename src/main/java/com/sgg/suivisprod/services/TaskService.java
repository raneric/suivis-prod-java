package com.sgg.suivisprod.services;

import static com.sgg.suivisprod.services.PaginationService.ROW_LIMIT;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.sgg.suivisprod.domain.Task;
import com.sgg.suivisprod.repository.TaskRepository;
import com.sgg.suivisprod.repository.UserRepository;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;

	public List<Task> findByUserAndPage(String userName, int currentPage) {
		Pageable   page           = PageRequest
				.of(currentPage - 1, ROW_LIMIT, Sort.by(Direction.DESC, "taskState", "finishedDate"));
		List<Task> taskListToview = this.taskRepository.findAllByUserName(userName, page);
		return taskListToview;
	}

	public int countAllUserTask(String username) {
		int totalTask = taskRepository.countAllTaskByUserName(username);
		return totalTask;
	}
	
}
