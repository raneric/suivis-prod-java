package com.sgg.suivisprod.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.sgg.suivisprod.domain.Task;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {
	@Query(value="{'user':{'userId':?0}}")
	List<Task> findByUserId(String userId, Pageable page);
	
	@Query(value="{'taskState':'todo', 'user':{'userId':?0}}")
	List<Task> findTodoTask(String userId, Pageable page);
	
	@Query(value="{'taskState':'in_progress', 'user':{'userId':?0}}")
	List<Task> findInProgressTask(String userId, Pageable page);
	
	@Query(value="{'taskState':'done', 'user':{'userId':?0}}")
	List<Task> findDoneTask(String userId, Pageable page);
	
	@Query(value="{'taskState':'stdby', 'user':{'userId':?0}}")
	List<Task> findStdByTask(String userId, Pageable page);
}
