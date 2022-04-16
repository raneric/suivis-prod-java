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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sgg.suivisprod.domain.Task;
import com.sgg.suivisprod.domain.TaskType;
import com.sgg.suivisprod.repository.TaskRepository;
import com.sgg.suivisprod.repository.TaskTypeRepository;

@Controller
@RequestMapping(TASK_PATH)
public class TaskController {

	@Autowired
	TaskTypeRepository taskTypeRepository;
	
	@Autowired
	TaskRepository taskRepository;

	@GetMapping(NEW_TASK_PATH)
	public String newTask(Model modelView) {
		TaskType majTask    = taskTypeRepository.findByTaskName(MAJ_TASK_TYPE);
		TaskType creaTask   = taskTypeRepository.findByTaskName(CREA_TASK_TYPE);
		TaskType othersTask = taskTypeRepository.findByTaskName(OTHERS_TASK_TYPE);

		modelView.addAttribute(MAJ_TASK_TYPE, majTask);
		modelView.addAttribute(CREA_TASK_TYPE, creaTask);
		modelView.addAttribute(OTHERS_TASK_TYPE, othersTask);
		return TASK_VIEW;
	}
	
	@PostMapping(NEW_TASK_PATH)
	public String newTask() {

		return TASK_VIEW;
	}
	
	
	@GetMapping("/{taskId}")
	public String openTask(@PathVariable int taskId,Model modelView) {
		TaskType majTask    = taskTypeRepository.findByTaskName(MAJ_TASK_TYPE);
		TaskType creaTask   = taskTypeRepository.findByTaskName(CREA_TASK_TYPE);
		TaskType othersTask = taskTypeRepository.findByTaskName(OTHERS_TASK_TYPE);

		Task currentTask = taskRepository.findOneByTaskId(taskId);
		
		modelView.addAttribute(MAJ_TASK_TYPE, majTask);
		modelView.addAttribute(CREA_TASK_TYPE, creaTask);
		modelView.addAttribute(OTHERS_TASK_TYPE, othersTask);
		
		modelView.addAttribute("task",currentTask);
		return TASK_VIEW;
	}

}
