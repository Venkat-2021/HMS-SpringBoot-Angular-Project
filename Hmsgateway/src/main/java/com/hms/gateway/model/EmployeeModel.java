package com.hms.gateway.model;

import java.time.LocalDate;
import java.util.List;

public class EmployeeModel {
	private long id; //Employee Id is Unique and Same as _id 
	private String employeeName; // Employee Name
	
	private String role; //Employee Role
	private String email;  // 
	
	private String password;
	
	private String gender;
	private LocalDate dob;
	private long number;
	private String address;
	
	private List<TaskModel> task;

	//NoArgs Constructor
	public EmployeeModel() {
		super();
	}

	//AllArgs Constructor
	public EmployeeModel(long id, String employeeName, String role, String email, String password,
			String gender, LocalDate dob, long number, String address, List<TaskModel> task) {
		super();
		this.id = id;
		this.employeeName = employeeName;
		this.role = role;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.dob = dob;
		this.number = number;
		this.address = address;
		this.task = task;
	}

	// Task Model Constructor
	public EmployeeModel(List<TaskModel> task) {
		super();
		this.task = task;
	}

	//Getters and Setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<TaskModel> getTask() {
		return task;
	}

	public void setTask(List<TaskModel> task) {
		this.task = task;
	}
	
	
	
	
	
	

	
}
