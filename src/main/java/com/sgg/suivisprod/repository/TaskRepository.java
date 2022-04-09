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
	@Query(value="{'user':{'userId':?0}}")
	List<Task> findByUserId(String userId, Pageable page);
	
	@Query(value="{'taskId':?0}")
	Task findOneByTaskId(int taskId);
	
	@Query(value="{'taskState':'todo', 'user':{'userId':?0}}")
	List<Task> findTodoTask(String userId, Pageable page);
	
	@Query(value="{'taskState':'in_progress', 'user':{'userId':?0}}")
	List<Task> findInProgressTask(String userId, Pageable page);
	
	@Query(value="{'taskState':'done', 'user':{'userId':?0}}")
	List<Task> findDoneTask(String userId, Pageable page);
	
	@Query(value="{'taskState':'stdby', 'user':{'userId':?0}}")
	List<Task> findStdByTask(String userId, Pageable page);
	
	@Aggregation(pipeline = {"{'$match': {'user':{'userId':?0}}}","{'$count': 'totalTask'}"})
	Integer countAllTask(String userId);
}
