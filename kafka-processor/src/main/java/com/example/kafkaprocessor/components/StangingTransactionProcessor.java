package com.example.kafkaprocessor.components;

import com.example.kafkaprocessor.data.entitities.CardHolderEntity;
import com.example.kafkaprocessor.model.StagingTransactionEntity;
import com.example.kafkaprocessor.data.repositories.CardHolderRepository;
import com.example.kafkaprocessor.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;

import java.util.List;
import java.util.stream.Collectors;

@EnableBinding(Processor.class)
public class StangingTransactionProcessor {

    @Autowired
    private CardHolderRepository cardHolderRepository;

    @Autowired
    private Processor processor;

    @StreamListener(Processor.INPUT)
    public void enrichStagingTransction(List<StagingTransactionEntity> stagingTransactionEntityList) {
        stagingTransactionEntityList.stream().forEach(e -> {
            CardHolderEntity cardHolderEntity = cardHolderRepository.getCardHolderByCardNumber(e.getCardNumber());
            Transaction transaction = Transaction.builder()
                    .name(cardHolderEntity.getName())
                    .surname(cardHolderEntity.getSurname())
                    .currency(e.getCurrency())
                    .date(e.getDate())
                    .transactionValue(e.getTransactionValue()).build();
            sendTransaction(transaction);
        });
    }

    private void sendTransaction(Transaction transaction) {
        processor.output().send(MessageBuilder.withPayload(transaction).build());
    }

}
