package com.example.finance_tracker_system.service.category;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.finance_tracker_system.dto.category.CategoryRequest;
import com.example.finance_tracker_system.dto.category.CategoryResponse;
import com.example.finance_tracker_system.entity.category.Category;
import com.example.finance_tracker_system.entity.security.User;
import com.example.finance_tracker_system.repo.category.CategoryRepository;
import com.example.finance_tracker_system.repo.security.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
	private final CategoryRepository categoryRepo;
	  private final UserRepository userRepo;

	  public CategoryResponse createCategory(CategoryRequest request, String username) {
	    User user = userRepo.findByUsername(username);
	    Category category = Category.builder()
	        .name(request.getName())
	        .type(request.getType())
	        .user(user)
	        .build();
	    categoryRepo.save(category);
	    return new CategoryResponse(category.getId(), category.getName(), category.getType());
	  }

	  public List<CategoryResponse> getUserCategories(String username) {
	    User user = userRepo.findByUsername(username);
	    return categoryRepo.findByUserId(user.getId()).stream()
	        .map(cat -> new CategoryResponse(cat.getId(), cat.getName(), cat.getType()))
	        .collect(Collectors.toList());
	  }
}
