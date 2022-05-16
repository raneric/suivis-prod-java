package com.sgg.suivisprod.services;

import static com.sgg.suivisprod.services.PaginationService.ROW_LIMIT;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.sgg.suivisprod.domain.Notification;
import com.sgg.suivisprod.domain.Task;
import com.sgg.suivisprod.domain.User;
import com.sgg.suivisprod.repository.TaskRepository;
import com.sgg.suivisprod.repository.UserRepository;
import com.sgg.suivisprod.utils.NotificationType;
import com.sgg.suivisprod.utils.TaskState;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private NotificationService notificationService;

	public List<Task> findByUserAndPage(String userName, int currentPage) {
		Pageable	page			= PageRequest.of(currentPage - 1, ROW_LIMIT,
				Sort.by(Direction.DESC, "taskState", "finishedDate"));
		List<Task>	taskListToview	= this.taskRepository.findAllByUserName(userName, page);
		return taskListToview;
	}

	public Map<String, List<Task>> findByTaskState(String username) {
		Map<String, List<Task>>	taskByTaskType	= new HashMap<>();
		Pageable				page			= PageRequest.of(0, 6, Sort.by(Direction.DESC, "startDate"));

		List<Task>	todo		= taskRepository.findByTaskState(username, TaskState.TODO.toString(), page);
		List<Task>	inprogress	= taskRepository.findByTaskState(username, TaskState.IN_PROGRESS.toString(), page);
		List<Task>	done		= taskRepository.findByTaskState(username, TaskState.DONE.toString(), page);
		List<Task>	stdby		= taskRepository.findByTaskState(username, TaskState.STAND_BY.toString(), page);

		taskByTaskType.put(TaskState.TODO.toString(), todo);
		taskByTaskType.put(TaskState.IN_PROGRESS.toString(), inprogress);
		taskByTaskType.put(TaskState.DONE.toString(), done);
		taskByTaskType.put(TaskState.STAND_BY.toString(), stdby);

		return taskByTaskType;
	}

	public int countAllUserTask(String username) {
		int totalTask = taskRepository.countAllTaskByUserName(username);
		return totalTask;
	}

	public String saveTask(Task task, String userName) {
		String taskId = "";
		if (task.getId() == null) {
			User user = userRepository.findByUsername(userName);
			task.setUser(user);
			taskId = insertNewTask(task);
		} else {
			taskId = updateTask(task);
		}
		return taskId;
	}

	private String insertNewTask(Task task) {
		Notification notif = new Notification(NotificationType.SUCCESS, "Task saved");
		notificationService.addNotification(notif);
		return taskRepository.save(task).getId();

	}

	private String updateTask(Task task) {
		return taskRepository.save(task).getId();
	}

	public void deteteTask(String taskId) {
		taskRepository.deleteById(taskId);
		Notification notif = new Notification(NotificationType.SUCCESS, "Task deleted");
		notificationService.addNotification(notif);
	}
}
