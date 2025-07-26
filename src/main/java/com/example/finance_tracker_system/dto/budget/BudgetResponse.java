package com.example.finance_tracker_system.dto.budget;

import java.time.YearMonth;

import lombok.*;

@Data
@AllArgsConstructor
public class BudgetResponse {
  private Long id;
  private double amount;
  private YearMonth month;
  private String categoryName;
}