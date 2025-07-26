package com.example.finance_tracker_system.entity.goal;

import java.time.LocalDate;

import com.example.finance_tracker_system.entity.security.User;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Goal {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;
  private double targetAmount;
  private double savedAmount;  // can be updated manually or from logic later
  private LocalDate deadline;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;
}
