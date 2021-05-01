package com.hms.employeeservice.service;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncoderService {
	
    public String passwordEncoders(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

}
