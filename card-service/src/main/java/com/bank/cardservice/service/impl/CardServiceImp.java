package com.bank.cardservice.service.impl;

import com.bank.cardservice.model.Card;
import com.bank.cardservice.repository.CardRepository;
import com.bank.cardservice.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardServiceImp implements CardService {

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

    public List<Card> getCardsByUserId(Long userId) {
        return cardRepository.findByClientId(userId);
    }

    public boolean changeLockedStatus(Long id) {
        Card card = cardRepository.findById(id).orElse(null);
        if (card == null) return false;
        card.setLocked(!card.getLocked());
        cardRepository.save(card);
        return true;
    }
}
