package com.hms.employeeservice;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import com.hms.employeeservice.model.EmployeeModel;
import com.hms.employeeservice.model.TaskModel;
import com.hms.employeeservice.repository.EmployeeRepository;

@DataMongoTest
public class EmployeeRepositoryTest {

	@Autowired
	private EmployeeRepository repository;
	
	
	@Test
	void itShouldSelectEmployeeByEmailExists() {
		List<TaskModel> task = new ArrayList<TaskModel>();
		LocalDateTime dateTime = LocalDateTime.now();
		LocalDate date = LocalDate.now();
		TaskModel task1 = new TaskModel("Test Task","Testing Repo Layer","Pending",dateTime);
		task.add(task1);
		EmployeeModel employee = new EmployeeModel(10, "sai","ADMIN" , "sai@email.com", "Sai123", "Male", date, 630007501L, "Hyderabad", task);
		
		repository.save(employee);
		Optional<EmployeeModel> optionalemp = repository.findByEmail("sai@email.com");
		assertThat(optionalemp).isPresent();
	}
	
	@Test
	void itShouldSelectEmployeeByRoleExists() {
		List<TaskModel> task = new ArrayList<TaskModel>();
		LocalDateTime dateTime = LocalDateTime.now();
		LocalDate date = LocalDate.now();
		TaskModel task1 = new TaskModel("Test Task","Testing Repo Layer","Pending",dateTime);
		task.add(task1);
		EmployeeModel employee = new EmployeeModel(10, "sai","ADMIN" , "sai@email.com", "Sai123", "Male", date, 630007501L, "Hyderabad", task);
		
		repository.save(employee);
		Optional<List<EmployeeModel>> optionalemp = repository.findByRole("ADMIN");
		assertThat(optionalemp).isPresent();
	}
	
}
