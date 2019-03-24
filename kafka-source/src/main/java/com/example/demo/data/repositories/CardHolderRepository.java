package com.example.demo.data.repositories;

import com.example.demo.data.entitities.CardHolderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardHolderRepository extends JpaRepository<CardHolderEntity, Long> {
}
