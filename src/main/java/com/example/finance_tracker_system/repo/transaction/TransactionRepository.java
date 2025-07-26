package com.example.finance_tracker_system.repo.transaction;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.finance_tracker_system.entity.transaction.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	List<Transaction> findByUserIdOrderByDateDesc(Long userId);
}
