package com.bank.cardservice.service.impl;

import com.bank.cardservice.model.Card;
import com.bank.cardservice.repository.CardRepository;
import com.bank.cardservice.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CardServiceImp implements CardService {

    private static final String USER_MODULE_URI = "http://localhost:9092/users/";
    private final WebClient webClient;
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

    public String getOwnerFullName(Long ownerId) {
        return webClient.get()
                .uri(USER_MODULE_URI + "/fullName/" + ownerId)
                .retrieve()
                .bodyToMono(String.class)
                .retryWhen(Retry.fixedDelay(3, Duration.ofMillis(100)))
                .block();
    }
}
