package com.example.finance_tracker_system.controller.category;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.finance_tracker_system.dto.category.CategoryRequest;
import com.example.finance_tracker_system.dto.category.CategoryResponse;
import com.example.finance_tracker_system.service.category.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
  private final CategoryService categoryService;

  // api for heallth check
  @GetMapping("/health")
  public ResponseEntity<String> healthCheck() {
	return ResponseEntity.ok("Category service is up and running");
  }
  
  @PostMapping
  public ResponseEntity<CategoryResponse> addCategory(@RequestBody CategoryRequest request) {
	  UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return ResponseEntity.ok(categoryService.createCategory(request, userDetails.getUsername()));
  }

  @GetMapping
  public ResponseEntity<List<CategoryResponse>> getCategories() {
	 UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return ResponseEntity.ok(categoryService.getUserCategories(userDetails.getUsername()));
  }
}