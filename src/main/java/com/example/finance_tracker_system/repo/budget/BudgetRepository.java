package com.example.finance_tracker_system.repo.budget;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.finance_tracker_system.entity.budget.Budget;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
	  List<Budget> findByUserIdAndMonth(Long userId, YearMonth month);
	  Optional<Budget> findByUserIdAndCategoryIdAndMonth(Long userId, Long categoryId, YearMonth month);
	}