package com.example.finance_tracker_system.service.budget;

import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.finance_tracker_system.dto.budget.BudgetRequest;
import com.example.finance_tracker_system.dto.budget.BudgetResponse;
import com.example.finance_tracker_system.entity.budget.Budget;
import com.example.finance_tracker_system.entity.category.Category;
import com.example.finance_tracker_system.entity.security.User;
import com.example.finance_tracker_system.repo.budget.BudgetRepository;
import com.example.finance_tracker_system.repo.category.CategoryRepository;
import com.example.finance_tracker_system.repo.security.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BudgetService {
  private final BudgetRepository budgetRepo;
  private final UserRepository userRepo;
  private final CategoryRepository categoryRepo;

  public BudgetResponse createOrUpdateBudget(BudgetRequest request, String username) {
    User user = userRepo.findByUsername(username);
    Category category = categoryRepo.findById(request.getCategoryId()).orElseThrow();

    Budget budget = budgetRepo.findByUserIdAndCategoryIdAndMonth(user.getId(), category.getId(), request.getMonth())
        .map(b -> {
          b.setAmount(request.getAmount());
          return b;
        })
        .orElse(Budget.builder()
            .user(user)
            .category(category)
            .month(request.getMonth())
            .amount(request.getAmount())
            .build());

    budgetRepo.save(budget);
    return new BudgetResponse(budget.getId(), budget.getAmount(), budget.getMonth(), category.getName());
  }

  public List<BudgetResponse> getUserBudgets(String username, YearMonth month) {
    User user = userRepo.findByUsername(username);

    return budgetRepo.findByUserIdAndMonth(user.getId(), month)
        .stream()
        .map(b -> new BudgetResponse(b.getId(), b.getAmount(), b.getMonth(), b.getCategory().getName()))
        .collect(Collectors.toList());
  }
}
