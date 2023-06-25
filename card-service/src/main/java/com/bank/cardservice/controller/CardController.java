package com.bank.cardservice.controller;

import com.bank.cardservice.service.CardService;
import com.bank.cardservice.model.Card;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")   //вот тут хз
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    @GetMapping
    public ResponseEntity<List<Card>> getCards() {
        List<Card> listCards = cardService.getAll();
        if(listCards != null) return ResponseEntity.ok().body(listCards);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Card> getCardById(@PathVariable Long id) {
       return ResponseEntity.ok(cardService.getCardById(id));
    }

    @PostMapping
    public ResponseEntity<String> addCard(Card card) {
        if (cardService.addCard(card)) return ResponseEntity.ok().body("Card has been successfully saved");
        return ResponseEntity.badRequest().body("Can't add card with id " + card.getId());
    }
}
