package com.example.finance_tracker_system.dto.category;

import com.example.finance_tracker_system.entity.category.CategoryType;

import lombok.Data;

@Data
public class CategoryRequest {
  private String name;
  private CategoryType type;
}