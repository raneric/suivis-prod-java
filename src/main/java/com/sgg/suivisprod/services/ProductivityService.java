package com.sgg.suivisprod.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.DoubleAdder;
import java.util.concurrent.atomic.LongAdder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgg.suivisprod.domain.Task;
import com.sgg.suivisprod.domain.TaskProductivity;
import com.sgg.suivisprod.domain.TaskType;
import com.sgg.suivisprod.domain.Variant;

@Service
public class ProductivityService {

	@Autowired
	private TaskService taskService;

	@Autowired
	private TaskTypeService taskTypeService;

	public void calculateTaskProductivity() {
		List<TaskType>						taskType	= taskTypeService.findAll();
		Map<String, List<Task>>				doneTask	= taskService.getFilteredTask("ran_eric");
		Map<String, List<TaskProductivity>>	allData		= new HashMap<>();

		for (int taskTypeIndex = 0; taskTypeIndex < taskType.size(); taskTypeIndex++) {
			TaskType				tempTaskType		= taskType.get(taskTypeIndex);
			List<TaskProductivity>	tempTaskProdList	= new ArrayList<TaskProductivity>();
			List<Variant>			tempTaskVarientList		= tempTaskType.getVariant();

			for (int taskVariantIndex = 0; taskVariantIndex < tempTaskVarientList.size(); taskVariantIndex++) {
				tempTaskProdList.add(getTaskProductivity(tempTaskVarientList.get(taskVariantIndex), doneTask));
			}
			allData.put(tempTaskType.getTaskName(), tempTaskProdList);
		}
		System.out.println("done");
	}

	private TaskProductivity getTaskProductivity(Variant taskVariant, Map<String, List<Task>> allTask) {

		List<Task>	tasks			= allTask.get(taskVariant.getTaskType());
		DoubleAdder	sumWorkingTime	= new DoubleAdder();
		LongAdder	sumNbAfter		= new LongAdder();
		if (tasks != null) {
			tasks.stream().forEach(tempTask -> {
				sumWorkingTime.add(tempTask.getTotalWorkingTime());
				sumNbAfter.add(tempTask.getBooth().getNbAfter());
			});
		}

		return new TaskProductivity(taskVariant.getTaskType(), sumWorkingTime.doubleValue(), sumNbAfter.intValue(),
				taskVariant.getProductivity());

	}
}
