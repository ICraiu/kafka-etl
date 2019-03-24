package com.example.kafkaprocessor.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StagingTransactionEntity {

    private Long id;

    @JsonProperty(value = "transaction_value")
    private BigDecimal transactionValue;

    private String currency;

    @JsonProperty(value = "card_number")
    private String cardNumber;

    private Timestamp date;

}
