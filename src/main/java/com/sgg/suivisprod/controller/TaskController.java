package com.sgg.suivisprod.controller;

import static com.sgg.suivisprod.utils.AppCont.NEW_TASK_PATH;
import static com.sgg.suivisprod.utils.AppCont.TASK_PATH;
import static com.sgg.suivisprod.utils.AppCont.TASK_VIEW;
import static com.sgg.suivisprod.utils.AppCont.MAJ_TASK_TYPE;
import static com.sgg.suivisprod.utils.AppCont.CREA_TASK_TYPE;
import static com.sgg.suivisprod.utils.AppCont.OTHERS_TASK_TYPE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sgg.suivisprod.domain.TaskType;
import com.sgg.suivisprod.repository.TaskTypeRepository;

@Controller
@RequestMapping(TASK_PATH)
public class TaskController {

	@Autowired
	TaskTypeRepository taskTypeRepository;

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

}
