package com.example.finance_tracker_system.controller.budget;

import java.time.YearMonth;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.finance_tracker_system.dto.budget.BudgetRequest;
import com.example.finance_tracker_system.dto.budget.BudgetResponse;
import com.example.finance_tracker_system.service.budget.BudgetService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/budgets")
@RequiredArgsConstructor
public class BudgetController {
  private final BudgetService budgetService;

  @PostMapping
  public ResponseEntity<BudgetResponse> setBudget(
      @RequestBody BudgetRequest request,
      @AuthenticationPrincipal UserDetails userDetails) {
    return ResponseEntity.ok(budgetService.createOrUpdateBudget(request, userDetails.getUsername()));
  }

  @GetMapping("/{yearMonth}")
  public ResponseEntity<List<BudgetResponse>> getBudgetsForMonth(
      @PathVariable String yearMonth,
      @AuthenticationPrincipal UserDetails userDetails) {
    YearMonth ym = YearMonth.parse(yearMonth); // format: YYYY-MM
    return ResponseEntity.ok(budgetService.getUserBudgets(userDetails.getUsername(), ym));
  }
}