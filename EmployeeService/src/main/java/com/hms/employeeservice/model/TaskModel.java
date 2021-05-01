package com.hms.employeeservice.model;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.index.Indexed;

/**
 * @author T V V Sai Narsimha Gupta
 * @email venkatsai2k17lpu@gmail.com
 * @desc Employee Task Details
 * 
 */
public class TaskModel {
	
	private String title;
	private String description;
	@Indexed
	private String status;
	private LocalDateTime createdAt;

	
	//NoArgsConstructor
	public TaskModel() {
		super();
	}

	//AllArgsConstructor
	public TaskModel(String title, String description, String status, LocalDateTime createdAt) {
		super();
		this.title = title;
		this.description = description;
		this.status = status;
		this.createdAt = createdAt;
	}
	
	//Getters and Setters
	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	
	
	
	
}
