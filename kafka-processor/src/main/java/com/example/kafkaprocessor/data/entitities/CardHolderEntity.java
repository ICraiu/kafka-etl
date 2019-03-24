package com.example.kafkaprocessor.data.entitities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "card_holder")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardHolderEntity {

    @Column
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    String cardNumber;
}
