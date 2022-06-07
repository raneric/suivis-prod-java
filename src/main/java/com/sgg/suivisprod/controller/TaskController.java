package com.sgg.suivisprod.controller;

import static com.sgg.suivisprod.constant.AppCont.CREA_TASK_TYPE;
import static com.sgg.suivisprod.constant.AppCont.MAJ_TASK_TYPE;
import static com.sgg.suivisprod.constant.AppCont.OTHERS_TASK_TYPE;
import static com.sgg.suivisprod.constant.PathConst.NEW_TASK_PATH;
import static com.sgg.suivisprod.constant.PathConst.TASK_PATH;
import static com.sgg.suivisprod.constant.PathConst.TASK_VIEW;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sgg.suivisprod.domain.Notification;
import com.sgg.suivisprod.domain.Task;
import com.sgg.suivisprod.domain.TaskType;
import com.sgg.suivisprod.repository.TaskRepository;
import com.sgg.suivisprod.services.NotificationService;
import com.sgg.suivisprod.services.TaskService;
import com.sgg.suivisprod.services.TaskTypeService;

@Controller
@RequestMapping(TASK_PATH)
public class TaskController {

	@Autowired
	TaskRepository taskRepository;

	@Autowired
	TaskTypeService taskTypeService;

	@Autowired
	TaskService taskService;

	@Autowired
	NotificationService notificationService;

	@GetMapping(NEW_TASK_PATH)
	public String newTask(Model modelView) {
		Task newTask = new Task();
		modelView.addAttribute("task", newTask);
		return TASK_VIEW;
	}

	@PostMapping(NEW_TASK_PATH)
	public String newTask(Task task, Principal userPrinicipal) {
		String taskId = taskService.saveNewTask(task, userPrinicipal.getName());
		return "redirect:/task/" + taskId;
	}

	@GetMapping("/{taskId}")
	public String editTask(@PathVariable String taskId, Model modelView) {
		Task currentTask = taskRepository.findById(taskId).get();
		modelView.addAttribute("task", currentTask);
		return TASK_VIEW;
	}

	@GetMapping("/delete/{taskId}")
	public String deleteTask(@PathVariable String taskId, Model modelView) {
		taskService.deteteTask(taskId);
		return "redirect:/task/new";
	}

	// -------------------TODO handle data to view -----------------------------
	@GetMapping("/asyncupdate/{taskId}")
	public @ResponseBody String ajaxRequestHandler(@PathVariable String taskId,
			@RequestParam(name = "state") String taskState) {
		Optional<Task> task = taskRepository.findById(taskId);
		task.get().setTaskState(taskState);
		taskService.updateTask(task.get());
		return "{\"data\":\"data sent\"}";
	}

	@ModelAttribute(CREA_TASK_TYPE)
	public TaskType populateCreaTaskType() {
		return taskTypeService.getCrea();
	}

	@ModelAttribute(MAJ_TASK_TYPE)
	public TaskType populateMajTaskType() {
		return taskTypeService.getMaj();
	}

	@ModelAttribute(OTHERS_TASK_TYPE)
	public TaskType populateOthersTaskType() {
		return taskTypeService.getOthers();
	}

	@ModelAttribute("notifications")
	public List<Notification> populateNotification(Principal userPrinicipal) {
		return notificationService.getAllNotifications(userPrinicipal.getName()).stream()
				.filter(notif -> !notif.isRead()).collect(Collectors.toList());
	}
}
