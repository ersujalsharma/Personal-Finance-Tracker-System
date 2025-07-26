package com.example.finance_tracker_system.entity.transaction;

import java.time.LocalDate;

import com.example.finance_tracker_system.entity.category.Category;
import com.example.finance_tracker_system.entity.security.User;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {
	 @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;

	  private double amount;
	  private String note;

	  private LocalDate date;

	  @ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name = "category_id")
	  private Category category;

	  @ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name = "user_id")
	  private User user;
}
