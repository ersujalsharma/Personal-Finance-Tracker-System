package com.example.finance_tracker_system.entity.security;

import lombok.Data;

@Data
public class RegisterRequest {
	private String username;
	  private String email;
	  private String password;
}
