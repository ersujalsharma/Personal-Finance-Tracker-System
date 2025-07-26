package com.example.finance_tracker_system.service.goal;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.finance_tracker_system.dto.goal.GoalRequest;
import com.example.finance_tracker_system.dto.goal.GoalResponse;
import com.example.finance_tracker_system.entity.goal.Goal;
import com.example.finance_tracker_system.entity.security.User;
import com.example.finance_tracker_system.repo.goal.GoalRepository;
import com.example.finance_tracker_system.repo.security.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GoalService {

  private final GoalRepository goalRepo;
  private final UserRepository userRepo;

  public GoalResponse createGoal(GoalRequest request, String username) {
    User user = userRepo.findByUsername(username);

    Goal goal = Goal.builder()
        .title(request.getTitle())
        .targetAmount(request.getTargetAmount())
        .savedAmount(request.getSavedAmount())
        .deadline(request.getDeadline())
        .user(user)
        .build();

    goalRepo.save(goal);
    return toResponse(goal);
  }

  public List<GoalResponse> getGoals(String username) {
    User user = userRepo.findByUsername(username);

    return goalRepo.findByUserId(user.getId())
        .stream()
        .map(this::toResponse)
        .collect(Collectors.toList());
  }

  public GoalResponse updateSavedAmount(Long id, double newAmount, String username) throws AccessDeniedException {
    User user = userRepo.findByUsername(username);
    Goal goal = goalRepo.findById(id).orElseThrow();

    if (!goal.getUser().getId().equals(user.getId())) {
      throw new AccessDeniedException("Unauthorized");
    }

    goal.setSavedAmount(newAmount);
    goalRepo.save(goal);
    return toResponse(goal);
  }

  private GoalResponse toResponse(Goal goal) {
    return new GoalResponse(goal.getId(), goal.getTitle(),
        goal.getTargetAmount(), goal.getSavedAmount(), goal.getDeadline());
  }
}