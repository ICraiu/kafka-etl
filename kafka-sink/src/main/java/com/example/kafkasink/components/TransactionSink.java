package com.example.kafkasink.components;

import com.example.kafkasink.data.entities.TransactionsEntity;
import com.example.kafkasink.data.repositories.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

import java.util.List;

@Slf4j
@EnableBinding(Sink.class)
public class TransactionSink {

    @Autowired
    private TransactionRepository transactionRepository;

    @StreamListener(Sink.INPUT)
    public void sinkTransactions(TransactionsEntity transaction) {
        log.info("Inserting transaction: " + transaction);
        transactionRepository.save(transaction);
    }
}
