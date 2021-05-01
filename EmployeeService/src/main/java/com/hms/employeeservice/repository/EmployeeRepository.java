package com.hms.employeeservice.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.hms.employeeservice.model.EmployeeModel;

@Repository
public interface EmployeeRepository extends MongoRepository<EmployeeModel, Long> {
	
	Optional<EmployeeModel> findByEmail(String email);
	
	Optional<List<EmployeeModel>> findByRole(String role);
}
