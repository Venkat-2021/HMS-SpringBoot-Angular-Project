package com.hms.employeeservice.repository;

import java.util.List;
import java.util.Optional;

import com.hms.employeeservice.model.EmployeeModel;
import com.hms.employeeservice.model.TaskModel;

public interface EmployeeService {
	
	//Method to add Employee into Database
	public EmployeeModel addEmployee(EmployeeModel employee);
	
	public List<EmployeeModel> getAllEmployees();
	
	public Optional<EmployeeModel> getEmployeebyId(long id);
	
	public Optional<List<EmployeeModel>> getEmployeebyRole(String Role);
	
	public EmployeeModel updateEmployeebyId(long id, EmployeeModel employee);
	
	public long deleteEmployeebyId(long id);
	
	public Optional<EmployeeModel> getEmployeebyEmail(String Email);
	
	public void setTaskById(long id, TaskModel task);
	
	public List<TaskModel> getTaskById(long id);
	
	

}
