package com.sgg.suivisprod.services;

import static com.sgg.suivisprod.constant.AppCont.CREA_TASK_TYPE;
import static com.sgg.suivisprod.constant.AppCont.MAJ_TASK_TYPE;
import static com.sgg.suivisprod.constant.AppCont.OTHERS_TASK_TYPE;
import static com.sgg.suivisprod.constant.AppCont.VALIDATION_TASK_TYPE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgg.suivisprod.domain.TaskType;
import com.sgg.suivisprod.repository.TaskTypeRepository;

@Service
public class TaskTypeService {
	@Autowired
	TaskTypeRepository taskTypeRepository;

	public List<TaskType> findAll(){
		return taskTypeRepository.findAll();
	}
	
	public TaskType getCrea() {
		return taskTypeRepository.findByTaskName(CREA_TASK_TYPE);
	}

	public TaskType getMaj() {
		return taskTypeRepository.findByTaskName(MAJ_TASK_TYPE);
	}

	public TaskType getOthers() {
		return taskTypeRepository.findByTaskName(OTHERS_TASK_TYPE);
	}

	public TaskType getValidation() {
		return taskTypeRepository.findByTaskName(VALIDATION_TASK_TYPE);
	}
}
