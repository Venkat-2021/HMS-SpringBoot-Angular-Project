package com.hms.employeeservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hms.employeeservice.exception.EmployeesCollectionException;
import com.hms.employeeservice.model.EmployeeModel;
import com.hms.employeeservice.model.TaskModel;
import com.hms.employeeservice.service.EmployeeServiceImpl;
import com.hms.employeeservice.service.SequenceGeneratorService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/employee")
public class EmployeeServiceController {
	
	@Autowired
	private EmployeeServiceImpl empservice;
	
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;
	
	@Operation(summary = "Get All Employees")
	@GetMapping("/get")
	public ResponseEntity<?> getEmployees(){
		List<EmployeeModel> employees = empservice.getAllEmployees();
		
		return new ResponseEntity<>(employees, employees.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND  );
	
	}
	
	@Operation(summary = "Add New Employee")
	@PostMapping("/add")
	public ResponseEntity<String> addEmployee( @Validated @RequestBody EmployeeModel employee) {
		try {
			empservice.addEmployee(employee);
			return ResponseEntity.ok("Employee Registered Suceesfully with Id: "+ employee.getId());
			}
			catch (Exception e) {
			sequenceGeneratorService.decrementSequence(EmployeeModel.SEQUENCE_NAME);
				 return new ResponseEntity<>("Employee Already Exist", HttpStatus.BAD_REQUEST);
			}
	}
	
	
	@Operation(summary = "Get Employee by ID")
	@GetMapping("/getby/{id}")
	public ResponseEntity<?> getEmployeeById(@PathVariable("id") long id){
		Optional<EmployeeModel> employees = empservice.getEmployeebyId(id);
		
		if (!employees.isPresent()) {
			return new ResponseEntity<>(EmployeesCollectionException.IdNotFoundException(id), HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(employees,HttpStatus.OK);
		}
		
	}
	
	
	@Operation(summary = "Get Employee by Role")
	@GetMapping("/findby/{role}")
	public ResponseEntity<?> getEmployeeByRole(@PathVariable(value ="role") String role){
		Optional<List<EmployeeModel>> employees = empservice.getEmployeebyRole(role);
		if ( !employees.isEmpty()) {
			return new ResponseEntity<>(employees,HttpStatus.OK);
		} else {
			
			return new ResponseEntity<>(EmployeesCollectionException.RoleNotFoundException(role), HttpStatus.NOT_FOUND);
		}
	}
	
	@Operation(summary = "Update Employee by Id")
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateEmployeeById(@PathVariable("id")long id, @Validated @RequestBody EmployeeModel employee){
		 try {
			
			 EmployeeModel employees=empservice.updateEmployeebyId(id, employee);
			 return ResponseEntity.ok("Employee Updated Sucessfully For Id :: "+employees.getId()); 
		 }
		 catch(Exception e) {
			return e.getMessage().contains("duplicate") ? new ResponseEntity<>(EmployeesCollectionException.EmployeeAlreadyExists(),
					HttpStatus.BAD_REQUEST): new ResponseEntity<>(EmployeesCollectionException.IdNotFoundException(id),HttpStatus.NOT_FOUND);
		 }
	}
	
	@Operation(summary = "Delete Employee by Id")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteEmployeeById(@PathVariable(value="id")long id) {
		  Optional<EmployeeModel> emp = empservice.getEmployeebyId(id);
		  try {
			  if(emp.isEmpty()) {
				  return new ResponseEntity<>(EmployeesCollectionException.IdNotFoundException(id), HttpStatus.NOT_FOUND);
		 }
			  empservice.deleteEmployeebyId(id);
			  return ResponseEntity.ok("Employee Deleted Sucessfully For Id"+id);
			 
	  }
		  catch (Exception e) {
			  return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		  }
	  }
	
	@Operation(summary = "Get Employee by Email")
	@GetMapping("/get/{email}")
	public ResponseEntity<?> getEmployeeByEmail(@PathVariable(value="email") String Email){
		Optional<EmployeeModel> employees = empservice.getEmployeebyEmail(Email);
		if (!employees.isPresent()) {
			return new ResponseEntity<>(EmployeesCollectionException.EmailNotFoundException(Email), HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(employees,HttpStatus.OK);
		}
		
	}
	
	@Operation(summary = "Set Employee task by Id")
	@PostMapping("/task/{id}")
	public ResponseEntity<?> setTaskById(@PathVariable("id") long id,@RequestBody TaskModel task){
		Optional<EmployeeModel> employees = empservice.getEmployeebyId(id);
		if (!employees.isPresent()) {
			return new ResponseEntity<>(EmployeesCollectionException.IdNotFoundException(id), HttpStatus.NOT_FOUND);
		} else {
			empservice.setTaskById(id, task);
			//return new ResponseEntity<>(employees.get().getTask(), HttpStatus.OK);
			return new ResponseEntity<>("Task Assigned to Employee with id ::" + id, HttpStatus.OK);
		}
	}
	
	@Operation(summary = "Get Employee task by Id")
	@GetMapping("/get/task/{id}")
	public ResponseEntity<?> getTaskById(@PathVariable("id") long id){
		Optional<EmployeeModel> employees = empservice.getEmployeebyId(id);
		if (!employees.isPresent()) {
			return new ResponseEntity<>(EmployeesCollectionException.IdNotFoundException(id), HttpStatus.NOT_FOUND);
		} else {
			
			return new ResponseEntity<>(employees.get().getTask(), HttpStatus.OK);
		}
	}
	
}
