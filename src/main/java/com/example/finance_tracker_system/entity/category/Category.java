package com.example.finance_tracker_system.entity.category;

import com.example.finance_tracker_system.entity.security.User;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;

	  private String name; // e.g. "Food", "Transport", "Salary"

	  @Enumerated(EnumType.STRING)
	  private CategoryType type; // INCOME or EXPENSE

	  @ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name = "user_id")
	  private User user;
}
