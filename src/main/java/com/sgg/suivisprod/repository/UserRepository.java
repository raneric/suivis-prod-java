package com.sgg.suivisprod.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sgg.suivisprod.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
	User findByUsername(String username);
}
