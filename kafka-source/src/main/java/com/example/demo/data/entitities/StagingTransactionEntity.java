package com.example.demo.data.entitities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "STG_TRANSACTION")
@Entity
public class StagingTransactionEntity {

    @Column
    @Id
    @GeneratedValue
    private Long id;

    @Column
    @JsonProperty(value = "transactionValue")
    private BigDecimal transactionValue;

    @Column
    private String currency;

    @Column
    @JsonProperty(value = "cardNumber")
    private String cardNumber;

    @Column
    private Timestamp date;

}
