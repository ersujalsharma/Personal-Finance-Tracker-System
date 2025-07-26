package com.example.finance_tracker_system.controller.goal;

import java.nio.file.AccessDeniedException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.finance_tracker_system.dto.goal.GoalRequest;
import com.example.finance_tracker_system.dto.goal.GoalResponse;
import com.example.finance_tracker_system.service.goal.GoalService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/goals")
@RequiredArgsConstructor
public class GoalController {
  private final GoalService goalService;

  @PostMapping
  public ResponseEntity<GoalResponse> createGoal(
      @RequestBody GoalRequest request,
      @AuthenticationPrincipal UserDetails userDetails) {
    return ResponseEntity.ok(goalService.createGoal(request, userDetails.getUsername()));
  }

  @GetMapping
  public ResponseEntity<List<GoalResponse>> getAllGoals(
      @AuthenticationPrincipal UserDetails userDetails) {
    return ResponseEntity.ok(goalService.getGoals(userDetails.getUsername()));
  }

  @PutMapping("/{id}/update-saved")
  public ResponseEntity<GoalResponse> updateSaved(
      @PathVariable Long id,
      @RequestParam double newAmount,
      @AuthenticationPrincipal UserDetails userDetails) throws AccessDeniedException {
    return ResponseEntity.ok(goalService.updateSavedAmount(id, newAmount, userDetails.getUsername()));
  }
}
