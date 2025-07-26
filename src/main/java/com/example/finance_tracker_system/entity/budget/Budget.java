package com.example.finance_tracker_system.entity.budget;

import java.time.YearMonth;

import com.example.finance_tracker_system.entity.category.Category;
import com.example.finance_tracker_system.entity.security.User;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Budget {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private double amount;

  private YearMonth month; // format: YYYY-MM

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id")
  private Category category;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;
}