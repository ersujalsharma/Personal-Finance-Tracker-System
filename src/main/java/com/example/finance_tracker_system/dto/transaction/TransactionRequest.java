package com.example.finance_tracker_system.dto.transaction;

import java.time.LocalDate;

import lombok.*;

@Data
public class TransactionRequest {
	private double amount;
	  private String note;
	  private LocalDate date;
	  private Long categoryId;
}
