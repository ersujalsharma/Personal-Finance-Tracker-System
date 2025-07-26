package com.example.finance_tracker_system.dto.transaction;

import java.time.LocalDate;

import com.example.finance_tracker_system.entity.category.CategoryType;

import lombok.*;
@Data
@AllArgsConstructor
public class TransactionResponse {
	private Long id;
	private double amount;
	private String note;
	private LocalDate date;
	private String categoryName;
	private CategoryType categoryType;
}
