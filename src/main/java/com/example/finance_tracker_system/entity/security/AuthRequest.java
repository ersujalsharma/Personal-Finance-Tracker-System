package com.example.finance_tracker_system.entity.security;

import lombok.Data;

@Data
public class AuthRequest {
	private String email;
	private String password;	
}
