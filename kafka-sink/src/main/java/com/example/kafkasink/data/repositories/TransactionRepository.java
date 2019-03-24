package com.example.kafkasink.data.repositories;

import com.example.kafkasink.data.entities.TransactionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository  extends JpaRepository<TransactionsEntity, Long> {
}
