package com.hms.employeeservice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import com.hms.employeeservice.model.EmployeeModel;
import com.hms.employeeservice.model.TaskModel;
import com.hms.employeeservice.repository.EmployeeRepository;
import com.hms.employeeservice.service.EmployeeServiceImpl;
import com.hms.employeeservice.service.PasswordEncoderService;
import com.hms.employeeservice.service.SequenceGeneratorService;

@DataMongoTest
public class EmployeeServiceTest {

	@Mock
	private EmployeeRepository repo;
	
	@Mock
	private SequenceGeneratorService seqService;
	
	@Mock
	private PasswordEncoderService passwordEncoderService;
	
	@InjectMocks
	private EmployeeServiceImpl empservicetest;
	
	
	@BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }
	LocalDate date = LocalDate.now();
     long id = 10;
	 String employeeName = "Sai";
	 String role = "Admin";
	 String email = "sai@email.com";
	 String password = "Hello125";
	 String gender = "male";
	 LocalDate dob = date;
	 long number;
	 String address;
	 List<TaskModel> task = new ArrayList<TaskModel>();
		LocalDateTime dateTime = LocalDateTime.now();
		TaskModel task1 = new TaskModel("Test Task","Testing Repo Layer","Pending",dateTime);
		
		EmployeeModel employee = new EmployeeModel(10, "sai","ADMIN" , "sai@email.com", "Sai123", "Male", date, 630007501L, "Hyderabad", task);
		EmployeeModel employee1 = new EmployeeModel(10, "sai","MANAGER" , "sai@email.com", "Sai123", "Male", date, 630007501L, "Hyderabad", task);
	 
	 @Test
	 public void addEmployee() {
		 	task.add(task1);
			empservicetest.addEmployee(employee);
			verify(repo, times(1)).save(employee);
	 }
	 
	 @Test
	 public void getAllUsers() {
		 
		 List<EmployeeModel> allemployees =new ArrayList<EmployeeModel>();
		 allemployees.add(employee);
		 allemployees.add(employee1);
		
		
		when(empservicetest.getAllEmployees()).thenReturn(allemployees);
	
		List<EmployeeModel> use=empservicetest.getAllEmployees();
		assertThat(use).isNotEmpty();
	}
	 
	@Test
	public void getAllEmployeesWhenEmpty() {
		List<EmployeeModel> allemployees =new ArrayList<EmployeeModel>();
		when(empservicetest.getAllEmployees()).thenReturn(allemployees);
		
		List<EmployeeModel> use=empservicetest.getAllEmployees();
		assertThat(use).isEmpty();
	}
	
	@Test
	public void getEmployeeById() {
		Optional<EmployeeModel> emp =Optional.of(new EmployeeModel(10, "Sai","MANAGER" , "sai@email.com", "Sai123", "Male", date, 630007501L, "Hyderabad", task));
		when(empservicetest.getEmployeebyId(10)).thenReturn(emp);
		
		EmployeeModel emps =empservicetest.getEmployeebyId(10).get();
		assertThat(emps.getEmployeeName()).isEqualTo(employeeName);
	}
	
	@Test
	public void donotgetEmployeeById() {
		when(empservicetest.getEmployeebyId(10)).thenReturn(Optional.empty());
		Optional<EmployeeModel> emps=empservicetest.getEmployeebyId(10);
		assertThat(emps).isEmpty();
	}
	
	@Test
	public void getEmployeeByEmail() {
		Optional<EmployeeModel> emp =Optional.of(new EmployeeModel(10, "Sai","MANAGER" , "sai@email.com", "Sai123", "Male", date, 630007501L, "Hyderabad", task));
		when(empservicetest.getEmployeebyEmail(email)).thenReturn(emp);
		
		EmployeeModel emps =empservicetest.getEmployeebyEmail(email).get();
		assertThat(emps.getEmployeeName()).isEqualTo(employeeName);
	}
	
	@Test
	public void donotgetEmployeeByEmail() {
		when(empservicetest.getEmployeebyEmail(email)).thenReturn(Optional.empty());
		Optional<EmployeeModel> emps=empservicetest.getEmployeebyEmail(email);
		assertThat(emps).isEmpty();
	}
	
	
	
	
	
}
