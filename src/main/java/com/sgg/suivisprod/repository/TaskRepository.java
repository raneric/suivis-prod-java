package com.sgg.suivisprod.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sgg.suivisprod.domain.Task;

@Repository
public interface TaskRepository extends PagingAndSortingRepository<Task, String> {
	@Query(value = "{'user':{'userId':?0}}")
	List<Task> findAllByUserId(String userId, Pageable page);
	
	@Query(value = "{'user.username':?0}")
	List<Task> findAllByUserName(String userName, Pageable page);
	
	@Query(value = "{'user':{'userId':?0},'taskState':?1}")
	List<Task> findByTaskType(String userId, String taskType, Pageable page);

	@Query(value = "{'taskId':?0}")
	Task findOneByTaskId(int taskId);

	@Aggregation(pipeline = {"{'$match':{'user.userId':?0}}", "{'$count':'totalTask'}"})
	Integer countAllTask(String userId);
	
	@Aggregation(pipeline = {"{'$match':{'user.username':?0}}", "{'$count':'totalTask'}"})
	Integer countAllTaskByUserName(String userName);
	
	@Aggregation(pipeline = {"{'$match':{'user.userId':?0,'taskState':?1}}", "{'$count':'totalTask'}"})
	Integer countTaskByTaskType(String userId, String taskState);
	
}
