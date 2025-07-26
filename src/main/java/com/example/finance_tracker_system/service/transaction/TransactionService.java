package com.example.finance_tracker_system.service.transaction;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.finance_tracker_system.dto.transaction.TransactionRequest;
import com.example.finance_tracker_system.dto.transaction.TransactionResponse;
import com.example.finance_tracker_system.entity.category.Category;
import com.example.finance_tracker_system.entity.security.User;
import com.example.finance_tracker_system.entity.transaction.Transaction;
import com.example.finance_tracker_system.repo.category.CategoryRepository;
import com.example.finance_tracker_system.repo.security.UserRepository;
import com.example.finance_tracker_system.repo.transaction.TransactionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionService {

  private final TransactionRepository transactionRepo;
  private final CategoryRepository categoryRepo;
  private final UserRepository userRepo;

  public TransactionResponse addTransaction(TransactionRequest request, String username) {
    User user = userRepo.findByUsername(username);
    Category category = categoryRepo.findById(request.getCategoryId()).orElseThrow();

    Transaction transaction = Transaction.builder()
        .amount(request.getAmount())
        .note(request.getNote())
        .date(request.getDate())
        .category(category)
        .user(user)
        .build();

    transactionRepo.save(transaction);

    return new TransactionResponse(transaction.getId(), transaction.getAmount(), transaction.getNote(),
        transaction.getDate(), category.getName(), category.getType());
  }

  public List<TransactionResponse> getUserTransactions(String username) {
    User user = userRepo.findByUsername(username);

    return transactionRepo.findByUserIdOrderByDateDesc(user.getId())
        .stream()
        .map(tx -> new TransactionResponse(tx.getId(), tx.getAmount(), tx.getNote(), tx.getDate(),
            tx.getCategory().getName(), tx.getCategory().getType()))
        .collect(Collectors.toList());
  }
}