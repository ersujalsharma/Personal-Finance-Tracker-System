package com.example.finance_tracker_system.dto.budget;

import java.time.YearMonth;

import lombok.Data;
@Data
public class BudgetRequest {
	private double amount;
	  private Long categoryId;
	  private YearMonth month;
}
