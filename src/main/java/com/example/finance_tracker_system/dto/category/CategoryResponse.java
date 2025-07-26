package com.example.finance_tracker_system.dto.category;

import com.example.finance_tracker_system.entity.category.CategoryType;

import lombok.*;

@Data
@AllArgsConstructor
public class CategoryResponse {
  private Long id;
  private String name;
  private CategoryType type;
}