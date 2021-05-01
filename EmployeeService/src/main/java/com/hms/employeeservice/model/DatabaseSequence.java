package com.hms.employeeservice.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;


/**
 * @author T V V Sai Narsimha Gupta
 * @email venkatsai2k17lpu@gmail.com
 * @desc Employee Id Sequence Generator
 * This Class (a collection in mongoDB )  will store the auto-incremented sequence for other collections.
 */

@Document (collection = "database_sequence")
public class DatabaseSequence {

	@MongoId
    private String id;

    private long sequence;

    public DatabaseSequence() {}

    //Getters and Setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getSequence() {
		return sequence;
	}

	public void setSequence(long sequence) {
		this.sequence = sequence;
	}
    
    
}
