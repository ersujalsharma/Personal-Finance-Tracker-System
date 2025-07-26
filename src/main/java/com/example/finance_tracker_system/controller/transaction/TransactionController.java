package com.example.finance_tracker_system.controller.transaction;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.finance_tracker_system.dto.transaction.TransactionRequest;
import com.example.finance_tracker_system.dto.transaction.TransactionResponse;
import com.example.finance_tracker_system.service.transaction.TransactionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

  private final TransactionService transactionService;

  @PostMapping
  public ResponseEntity<TransactionResponse> addTransaction(
      @RequestBody TransactionRequest request,
      @AuthenticationPrincipal UserDetails userDetails) {
	  System.out.println("add transaction");
	  return ResponseEntity.ok(transactionService.addTransaction(request, userDetails.getUsername()));
  }

  @GetMapping
  public ResponseEntity<List<TransactionResponse>> getTransactions(
      @AuthenticationPrincipal UserDetails userDetails) {
    return ResponseEntity.ok(transactionService.getUserTransactions(userDetails.getUsername()));
  }
}
