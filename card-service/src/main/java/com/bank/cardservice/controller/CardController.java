package com.bank.cardservice.controller;

import com.bank.cardservice.service.CardService;
import com.bank.cardservice.model.Card;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    @GetMapping
    public ResponseEntity<List<Card>> getCards(@RequestParam (required=false) Long userId) {
        if(userId != null) return ResponseEntity.ok(cardService.getCardsByUserId(userId));
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

    @PostMapping("/block/{id}")
    public ResponseEntity<String> changeLockedStatus(@PathVariable Long id) {
        if (cardService.changeLockedStatus(id)) return ResponseEntity.ok().body("Card's status has been successfully changed");
        return ResponseEntity.badRequest().body("Can't change status of card with id " + id);
    }


}
