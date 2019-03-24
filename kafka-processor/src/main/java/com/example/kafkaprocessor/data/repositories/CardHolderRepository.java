package com.example.kafkaprocessor.data.repositories;

import com.example.kafkaprocessor.data.entitities.CardHolderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardHolderRepository extends JpaRepository<CardHolderEntity, Long> {
    CardHolderEntity getCardHolderByCardNumber(String cardNumber);
}
