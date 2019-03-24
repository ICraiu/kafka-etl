package com.example.kafkaprocessor.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    private String name;
    private String surname;
    private BigDecimal transactionValue;
    private String currency;
    private Timestamp date;
}
