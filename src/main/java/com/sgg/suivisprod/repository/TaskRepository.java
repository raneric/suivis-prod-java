package com.sgg.suivisprod.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.sgg.suivisprod.domain.Task;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {
	@Query(value="{'user':{'userId':'Ht1GcigN1YRwzFjOWuN8J9WUf6x2'}}")
	List<Task> findAll(String user);
}
