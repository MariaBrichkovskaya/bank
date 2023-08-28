package com.bank.cardservice.service.impl;

import com.bank.cardservice.exceptions.CardExistingNumberException;
import com.bank.cardservice.exceptions.CardNotFoundException;
import com.bank.cardservice.model.Card;
import com.bank.cardservice.repository.CardRepository;
import com.bank.cardservice.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
@Primary
public class CardServiceImp implements CardService {

    private static final String USER_MODULE_URI = "http://localhost:9092/users/";
    private final WebClient webClient;
    private final CardRepository cardRepository;
    public Card getCardById(Long id) {
        if(cardRepository.findById(id).isEmpty()){
            throw new CardNotFoundException("Card does not exist");
        }
        return cardRepository.findById(id).get();
    }

    public boolean addCard(Card card) {
        if (cardRepository.findByNumber(card.getNumber())==null)
            throw new CardExistingNumberException("this number is exist");
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
        if(cardRepository.findById(id).isEmpty()){
            throw new CardNotFoundException("Card does not exist");
        }
        Card card = cardRepository.findById(id).orElse(null);
        if (card == null) return false;
        card.setLocked(!card.getLocked());
        cardRepository.save(card);
        return true;
    }

    public String getOwnerFullName(Long ownerId) {
        return webClient.get()
                .uri(USER_MODULE_URI + "/fullName/" + ownerId)
                .retrieve()
                .bodyToMono(String.class)
                .retryWhen(Retry.fixedDelay(3, Duration.ofMillis(100)))
                .block();
    }
}
