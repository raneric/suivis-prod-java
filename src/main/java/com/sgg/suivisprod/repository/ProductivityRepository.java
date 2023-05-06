package com.sgg.suivisprod.repository;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.sgg.suivisprod.domain.TaskProductivity;

public interface ProductivityRepository extends MongoRepository<TaskProductivity, String>  {

	@Aggregation(pipeline = {
			"{ '$match': { 'user.username':?0, 'taskState': 'done' } }", "{ '$group': { '_id': null, 'sumWorkingTime': { '$sum': '$totalWorkingTime' },'sumNbAfter': {'$sum': '$booth.nbAfter'} } }" })
	TaskProductivity sumAllTaskTotalWorkingTime(String userName);
}
