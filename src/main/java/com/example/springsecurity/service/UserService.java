package com.example.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.springsecurity.entity.User;
import com.example.springsecurity.repo.UserRepository;


@Service
public class UserService {
	private final UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	public void createUser(String username, String password) {
		User user = new User();
		if(userRepository.findByUsername(username) != null) {
			throw new IllegalArgumentException("User already exists");
		}
		user.setUsername(username);
		user.setPassword(passwordEncoder.encode(password)); // Encrypt the password
		userRepository.save(user);
	}
	public boolean saveNewUser(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
//            user.setRoles(Arrays.asList("USER"));
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
	// login
	public User authenticate(String username,String password) {
		User user = userRepository.findByUsername(username);
		if(user == null) {
			throw new IllegalArgumentException("User not found");
		}
		if(!passwordEncoder.matches(password, user.getPassword())) {
			throw new IllegalArgumentException("Invalid password");
		}
		return user;
	}
	// fetch all users
	public Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}
	
}
