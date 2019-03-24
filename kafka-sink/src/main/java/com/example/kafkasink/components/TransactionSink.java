package com.example.kafkasink.components;

import com.example.kafkasink.data.entities.TransactionsEntity;
import com.example.kafkasink.data.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

import java.util.List;

@EnableBinding(Sink.class)
public class TransactionSink {

    @Autowired
    private TransactionRepository transactionRepository;

    @StreamListener(Sink.INPUT)
    public void sinkTransactions(List<TransactionsEntity> transactionList) {
        transactionList.forEach(transactionRepository::save);
    }
}
