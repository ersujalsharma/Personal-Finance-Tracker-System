package com.example.finance_tracker_system.repo.category;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.finance_tracker_system.entity.category.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
  List<Category> findByUserId(Long userId);
}