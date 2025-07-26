package com.example.finance_tracker_system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.finance_tracker_system.service.security.UserService;

@SpringBootApplication
public class Personal_Finanace_Tracker_System{
	@Autowired
	UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(Personal_Finanace_Tracker_System.class, args);
	}


}
