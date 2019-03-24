package com.example.kafkaprocessor.components;

import com.example.kafkaprocessor.data.entitities.CardHolderEntity;
import com.example.kafkaprocessor.model.StagingTransactionEntity;
import com.example.kafkaprocessor.data.repositories.CardHolderRepository;
import com.example.kafkaprocessor.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.SendTo;

import java.util.List;
import java.util.stream.Collectors;

@EnableBinding(Processor.class)
public class StangingTransactionProcessor {

    @Autowired
    private CardHolderRepository cardHolderRepository;

    @StreamListener(Processor.INPUT)
    @SendTo(Processor.OUTPUT)
    public List<Transaction> enrichStagingTransction(List<StagingTransactionEntity> stagingTransactionEntityList) {
        return stagingTransactionEntityList.stream().map(e -> {
            CardHolderEntity cardHolderEntity = cardHolderRepository.getCardHolderByCardNumber(e.getCardNumber());
            return Transaction.builder()
                    .name(cardHolderEntity.getName())
                    .surname(cardHolderEntity.getSurname())
                    .currency(e.getCurrency())
                    .date(e.getDate())
                    .transactionValue(e.getTransactionValue()).build();
        }).collect(Collectors.toList());
    }

}
