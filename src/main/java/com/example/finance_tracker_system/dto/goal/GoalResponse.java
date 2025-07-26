package com.example.finance_tracker_system.dto.goal;

import java.time.LocalDate;

import lombok.*;

@Data
@AllArgsConstructor
public class GoalResponse {
  private Long id;
  private String title;
  private double targetAmount;
  private double savedAmount;
  private LocalDate deadline;
}