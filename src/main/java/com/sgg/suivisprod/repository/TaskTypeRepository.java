package com.sgg.suivisprod.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.sgg.suivisprod.domain.TaskType;

public interface TaskTypeRepository extends MongoRepository<TaskType, String> {
	
	@Query(value="{'taskName':?0}")
	TaskType findByTaskName(String taskName);
}
