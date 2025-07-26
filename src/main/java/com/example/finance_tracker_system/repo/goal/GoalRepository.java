package com.example.finance_tracker_system.repo.goal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.finance_tracker_system.entity.goal.Goal;

public interface GoalRepository extends JpaRepository<Goal, Long> {
	  List<Goal> findByUserId(Long userId);
	}