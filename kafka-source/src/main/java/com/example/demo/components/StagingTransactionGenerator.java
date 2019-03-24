package com.example.demo.components;


import com.example.demo.data.entitities.StagingTransactionEntity;
import com.example.demo.data.repositories.CardHolderRepository;
import com.example.demo.data.repositories.StagingTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component
@EnableScheduling
public class StagingTransactionGenerator {

    private StagingTransactionRepository stagingTransactionRepository;
    private final String[] currencies = {"RON", "USD", "GBP", "EUR", "DKK"};
    private Random random;
    private List cardNumberList;


    @Autowired
    public StagingTransactionGenerator(StagingTransactionRepository stagingTransactionRepository, CardHolderRepository cardHolderRepository) {
        this.stagingTransactionRepository = stagingTransactionRepository;
        this.random = new Random();
        this.cardNumberList = cardHolderRepository.findAll().stream().map(e -> e.getCardNumber()).collect(Collectors.toList());
    }


    @Scheduled(fixedRate = 200)
    private void generateTransaction() {
        this.stagingTransactionRepository.save(StagingTransactionEntity
                .builder()
                .cardNumber((String) this.cardNumberList.get(this.random.nextInt(6)))
                .currency(this.currencies[this.random.nextInt(5)])
                .date(new Timestamp(new Date().getTime()))
                .transactionValue(new BigDecimal(this.random.nextDouble() * this.random.nextInt(20000)))
                .build()
        );
    }
}

