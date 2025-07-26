package com.example.finance_tracker_system.service.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.finance_tracker_system.entity.security.AuthRequest;
import com.example.finance_tracker_system.entity.security.AuthResponse;
import com.example.finance_tracker_system.entity.security.RegisterRequest;
import com.example.finance_tracker_system.entity.security.Role;
import com.example.finance_tracker_system.entity.security.User;
import com.example.finance_tracker_system.repo.security.UserRepository;
import com.example.finance_tracker_system.util.JwtUtil;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class AuthService {
	private final UserRepository userRepo;
	  private final PasswordEncoder passwordEncoder;
	  private final JwtUtil jwtUtil;
	  private final AuthenticationManager authManager;
	
	  public AuthResponse register(RegisterRequest req) {
	    User user = User.builder()
	      .username(req.getUsername())
	      .email(req.getEmail())
	      .password(passwordEncoder.encode(req.getPassword()))
	      .role(Role.USER)
	      .build();
	
	    userRepo.save(user);
	    String token = jwtUtil.generateToken(user.getUsername());
	    return new AuthResponse(token);
	  }
	
	  public AuthResponse authenticate(AuthRequest req) {
	    try {
		  authManager.authenticate(new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));
	    
	    } catch (Exception e) {
	      System.out.println(e.getMessage());
	    }
	    User user = userRepo.findByEmail(req.getEmail()).orElseThrow();
	    
	    String token = jwtUtil.generateToken(user.getUsername());
	    return new AuthResponse(token);
	  }
}
