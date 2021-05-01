package com.hms.employeeservice.service;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.hms.employeeservice.model.DatabaseSequence;

import java.util.Objects;

@Service
public class SequenceGeneratorService {
	
	private MongoOperations mongoOperations;
	
	@Autowired
	public SequenceGeneratorService(MongoOperations mongoOperations) {
		this.mongoOperations = mongoOperations;
	}
	
	public long generateSequence(String sequenceName) {
		//Get Sequence No
        Query query = new Query(Criteria.where("id").is(sequenceName));
        //Update the Sequence No
        Update update = new Update().inc("sequence", 1);
        //Modify the Sequence in the Employees Document
        DatabaseSequence counter = mongoOperations
                .findAndModify(query,
                        update, options().returnNew(true).upsert(true),
                        DatabaseSequence.class);

        return !Objects.isNull(counter) ? counter.getSequence() : 1;
	}

	public void decrementSequence(String sequenceName) {
		// Decrement the Sequence when no data is inserted into the Document 
		mongoOperations.findAndModify(query(where("_id").is(sequenceName)),
                new Update().inc("sequence",-1), options().returnNew(true).upsert(true),
                DatabaseSequence.class);

	}
	
}
