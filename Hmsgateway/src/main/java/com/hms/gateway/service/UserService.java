package com.hms.gateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hms.gateway.model.EmployeeModel;
import com.hms.gateway.security.LoginUserDetails;

@Service
public class UserService implements UserDetailsService {
	
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 final String uri = "http://EMPLOYEE-SERVICE/employee/get/"+username;

		 EmployeeModel employee = restTemplate.getForObject(uri,  EmployeeModel .class);
		
		 return LoginUserDetails.build(employee);
	}

}
