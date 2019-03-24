package com.example.demo.components;

import com.example.demo.data.entitities.CardHolderEntity;
import com.example.demo.data.repositories.CardHolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CardHolderDbInitializer {

    private CardHolderRepository cardHolderRepository;

    @Autowired
    public CardHolderDbInitializer(CardHolderRepository cardHolderRepository) {
        this.cardHolderRepository = cardHolderRepository;
        cardHolderRepository.save(CardHolderEntity
                .builder()
                .cardNumber("4256031125378892")
                .name("Jack")
                .surname("O'Neill")
                .build());

        cardHolderRepository.save(CardHolderEntity
                .builder()
                .cardNumber("8302837362938277")
                .name("Daniel")
                .surname("Jackson")
                .build());

        cardHolderRepository.save(CardHolderEntity
                .builder()
                .cardNumber("8824299382736628")
                .name("Samantha")
                .surname("Carter")
                .build());

        cardHolderRepository.save(CardHolderEntity
                .builder()
                .cardNumber("8812736172723612")
                .name("Teal")
                .surname("'c")
                .build());

        cardHolderRepository.save(CardHolderEntity
                .builder()
                .cardNumber("3929172337172722")
                .name("George")
                .surname("Hammond")
                .build());

        cardHolderRepository.save(CardHolderEntity
                .builder()
                .cardNumber("7718288821992214")
                .name("Vala")
                .surname("Mal Doran")
                .build());


    }
}
