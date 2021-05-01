package com.hms.employeeservice.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.employeeservice.model.EmployeeModel;
import com.hms.employeeservice.model.TaskModel;
import com.hms.employeeservice.repository.EmployeeRepository;
import com.hms.employeeservice.repository.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository repository;
	
	@Autowired
	private SequenceGeneratorService sequenceService;
	
	@Autowired
	private PasswordEncoderService passencoder;

	@Override
	public EmployeeModel addEmployee(EmployeeModel employee) {
		employee.setId(sequenceService.generateSequence(EmployeeModel.SEQUENCE_NAME));
		employee.setPassword(passencoder.passwordEncoders(employee.getPassword()));
		return repository.save(employee);
	}

	@Override
	public List<EmployeeModel> getAllEmployees() {
		return repository.findAll();
	}

	@Override
	public Optional<EmployeeModel> getEmployeebyId(long id) {
		return repository.findById(id);
	}

	@Override
	public Optional<List<EmployeeModel>> getEmployeebyRole(String Role) {
		
		return repository.findByRole(Role);
	}

	@Override
	public EmployeeModel updateEmployeebyId(long id, EmployeeModel employee) {
		Optional<EmployeeModel> employees = repository.findById(id);
		EmployeeModel updateemp = employees.get();
		
		updateemp.setEmployeeName(employee.getEmployeeName());
		updateemp.setDob(employee.getDob());
		updateemp.setNumber(employee.getNumber());
		updateemp.setAddress(employee.getAddress());
		updateemp.setGender(employee.getGender());
		updateemp.setRole(employee.getRole());
		return repository.save(updateemp);
	}

	@Override
	public long deleteEmployeebyId(long id) {
		repository.deleteById(id);
		return id;
	}

	@Override
	public Optional<EmployeeModel> getEmployeebyEmail(String Email) {
		return repository.findByEmail(Email);
	}

	@Override
	public void setTaskById(long id, TaskModel task) {
		EmployeeModel employee = repository.findById(id).get();
		List<TaskModel> taskId = employee.getTask();
		
		taskId.add(task);
		repository.save(employee);
	}

	@Override
	public List<TaskModel> getTaskById(long id) {
		EmployeeModel employee = repository.findById(id).get();
		
		return employee.getTask();
	}


	

	

}
