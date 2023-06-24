package com.bank.cardservice.service;

import com.bank.cardservice.model.Card;
import com.bank.cardservice.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    public Card getCardById(Long id) {
        return cardRepository.getById(id);
    }

    public boolean addCard(Card card) {
        //if (getCardByNumber(card.getId()) != null) return false;
        cardRepository.save(card);
        return true;
    }

    public List<Card> getAll() {
        return cardRepository.findAll();
    }
}
