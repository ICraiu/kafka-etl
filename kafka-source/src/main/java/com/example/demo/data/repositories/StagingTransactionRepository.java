package com.example.demo.data.repositories;

import com.example.demo.data.entitities.StagingTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StagingTransactionRepository extends JpaRepository<StagingTransactionEntity, Long> {
}
