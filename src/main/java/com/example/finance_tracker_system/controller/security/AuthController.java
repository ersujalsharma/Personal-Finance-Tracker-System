package com.example.finance_tracker_system.controller.security;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.finance_tracker_system.entity.security.AuthRequest;
import com.example.finance_tracker_system.entity.security.AuthResponse;
import com.example.finance_tracker_system.entity.security.RegisterRequest;
import com.example.finance_tracker_system.service.security.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
	private final AuthService authService;

	  @PostMapping("/register")
	  public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest req) {
	    return ResponseEntity.ok(authService.register(req));
	  }

	  @PostMapping("/login")
	  public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest req) {
	    return ResponseEntity.ok(authService.authenticate(req));
	  }
}
