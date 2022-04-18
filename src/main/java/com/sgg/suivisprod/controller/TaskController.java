package com.sgg.suivisprod.controller;

import static com.sgg.suivisprod.constant.AppCont.CREA_TASK_TYPE;
import static com.sgg.suivisprod.constant.AppCont.MAJ_TASK_TYPE;
import static com.sgg.suivisprod.constant.AppCont.OTHERS_TASK_TYPE;
import static com.sgg.suivisprod.constant.PathConst.NEW_TASK_PATH;
import static com.sgg.suivisprod.constant.PathConst.TASK_PATH;
import static com.sgg.suivisprod.constant.PathConst.TASK_VIEW;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sgg.suivisprod.domain.Task;
import com.sgg.suivisprod.domain.TaskType;
import com.sgg.suivisprod.repository.TaskRepository;
import com.sgg.suivisprod.services.TaskTypeService;

@Controller
@RequestMapping(TASK_PATH)
public class TaskController {

	@Autowired
	TaskRepository taskRepository;

	@Autowired
	TaskTypeService taskTypeService;

	@GetMapping(NEW_TASK_PATH)
	public String newTask(Model modelView) {
		return TASK_VIEW;
	}

	//------------------TODO handle submit request--------------------
	@PostMapping(NEW_TASK_PATH)
	public String newTask() {
		return TASK_VIEW;
	}

	@GetMapping("/{taskId}")
	public String openTask(@PathVariable int taskId, Model modelView) {
		Task currentTask = taskRepository.findOneByTaskId(taskId);
		modelView.addAttribute("task", currentTask);
		return TASK_VIEW;
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
}
