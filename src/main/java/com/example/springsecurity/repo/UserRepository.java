package com.example.springsecurity.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springsecurity.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}
