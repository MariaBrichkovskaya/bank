package com.bank.cardservice.service;

import com.bank.cardservice.model.Card;

import java.util.List;

public interface CardService {
    Card getCardById(Long id);
    boolean addCard(Card card);
    List<Card> getAll();
    List<Card> getCardsByUserId(Long userId);
    boolean changeLockedStatus(Long id);

    String getOwnerFullName(Long userId);
}
