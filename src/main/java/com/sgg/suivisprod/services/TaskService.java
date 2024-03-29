package com.sgg.suivisprod.services;

import static com.sgg.suivisprod.services.PaginationService.ROW_LIMIT;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

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

	/**
	 * Find all user task by the current request table page
	 * 
	 * @param userName
	 * @param currentPage
	 * @return
	 */
	public List<Task> findByUserAndPage(String userName, int currentPage) {
		Pageable	page			= PageRequest.of(currentPage - 1, ROW_LIMIT,
				Sort.by(Direction.DESC, "taskState", "finishedDate"));
		List<Task>	taskListToview	= this.taskRepository.findAllByUserName(userName, page);
		return taskListToview;
	}

	public List<Task> findDoneTask(String userName) {
		List<Task>	allTask		= taskRepository.findAllByUserName(userName);
		List<Task>	taskDone	= allTask.stream().filter(task -> task.getTaskState().equals("done"))
				.collect(Collectors.toList());
		return taskDone;
	}

	/**
	 * find and group task by task type
	 * 
	 * @param username
	 * @return
	 */
	public Map<String, List<Task>> findTaskGroupedByState(String username) {
		Map<String, List<Task>>	taskBytaskState	= new HashMap<>();
		Pageable				pageDescOrder	= PageRequest.of(0, 7, Sort.by(Direction.DESC, "startDate"));
		Pageable				pageAscOrder	= PageRequest.of(0, 7);

		List<Task>	todo		= taskRepository.findByTaskState(username, TaskState.TODO.toString(), pageAscOrder);
		List<Task>	inprogress	= taskRepository.findByTaskState(username, TaskState.IN_PROGRESS.toString(),
				pageAscOrder);
		List<Task>	done		= taskRepository.findByTaskState(username, TaskState.DONE.toString(), pageDescOrder);
		List<Task>	stdby		= taskRepository.findByTaskState(username, TaskState.STAND_BY.toString(), pageAscOrder);

		taskBytaskState.put(TaskState.TODO.toString(), todo);
		taskBytaskState.put(TaskState.IN_PROGRESS.toString(), inprogress);
		taskBytaskState.put(TaskState.DONE.toString(), done);
		taskBytaskState.put(TaskState.STAND_BY.toString(), stdby);

		return taskBytaskState;
	}

	/**
	 * 
	 * @param username
	 * @return
	 */
	public int countAllUserTask(String username) {
		int totalTask = taskRepository.countAllTaskByUserName(username);
		return totalTask;
	}

	/**
	 * Save new task or update existing one
	 * 
	 * @param task
	 * @param userName
	 * @return
	 */
	public String saveTask(Task task, String userName) {
		String taskId = new String();
		if (task.getId() == null) {
			taskId = saveNewTask(task, userName);
		} else {
			taskId = updateTask(task);
		}
		return taskId;
	}

	/**
	 * Insert nes task to DB
	 * 
	 * @param Task   task
	 * @param String userName
	 * @return String task ID
	 */
	private String saveNewTask(Task task, String userName) {
		User user = userRepository.findByUsername(userName);
		task.setUser(user);
		String taskId = taskRepository.save(task).getId();
		notificationService.notify(NotificationType.SUCCESS, "New task saved", user);
		return taskId;
	}

	/**
	 * update existing task
	 * 
	 * @param Task task
	 * @return String task ID
	 */
	public String updateTask(Task task) {
		String taskId = taskRepository.save(task).getId();
		notificationService.notify(NotificationType.SUCCESS, "New task updated", task.getUser());
		return taskId;
	}

	public void updateTaskState(String taskId, String taskState) {
		Optional<Task> task = taskRepository.findById(taskId);
		if (!task.isEmpty()) {
			Task tempTask = task.get();
			tempTask.setTaskState(taskState);

			if (taskState.equals(TaskState.IN_PROGRESS.toString()) && tempTask.getStartDate() == null) {
				tempTask.setStartDate(new Date());
			} else if (taskState.equals(TaskState.DONE.toString())) {
				tempTask.setFinishedDate(new Date());
			}
			updateTask(tempTask);
		}
	}

	/**
	 * 
	 * @param taskId
	 */
	public void deteteTask(String taskId) {
		User user = taskRepository.findById(taskId).get().getUser();
		taskRepository.deleteById(taskId);
		notificationService.notify(NotificationType.SUCCESS, "Task deleted", user);
	}

	public Map<String, List<Task>> getFilteredTask(String userName) {
		List<Task>		allTask					= taskRepository.findAllByUserName(userName);
		Predicate<Task>	doneTaskWithWorkingTime	= task -> (task.getTaskState().equals(TaskState.DONE.toString())
				&& task.getTotalWorkingTime() != 0);

		List<Task> doneTask = allTask.stream().filter(doneTaskWithWorkingTime).collect(Collectors.toList());

		return doneTask.stream().collect(Collectors.groupingBy(task -> {
			if (task.getTaskType().indexOf("Prio") > 0) {
				return task.getTaskType().substring(0, task.getTaskType().indexOf("Prio"));
			} else {
				return task.getTaskType();
			}
		}));
	}
}
