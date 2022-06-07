package com.sgg.suivisprod.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.sgg.suivisprod.domain.Notification;

@Repository
public interface NotificationRepository extends MongoRepository<Notification, String> {
	
	@Query(value = "{'user.username':?0}")
	List<Notification> findByuserName(String username);
}