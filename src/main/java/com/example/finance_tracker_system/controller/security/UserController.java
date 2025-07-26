package com.example.finance_tracker_system.controller.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.finance_tracker_system.entity.security.User;
import com.example.finance_tracker_system.service.security.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	private final UserService userService;
	public UserController(UserService userService) {
		this.userService = userService;
	}
	// Define endpoints for user operations here
	@PostMapping("/create")
	public String createUser(@RequestBody User user) {
		// Logic to create a user
		userService.createUser(user.getUsername(),user.getPassword());
		return "User created successfully";
	}
	@PostMapping("/login")
	public User authenticate(@RequestBody User user) {
		// Logic to find a user by username
		return userService.authenticate(user.getUsername(),user.getPassword());
	}
	// fetch all users
	@GetMapping("/all")
	public Iterable<User> getAllUsers() {
		// Logic to fetch all users
		return userService.getAllUsers();
	}
}
