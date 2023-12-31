package com.bank.cardservice.controller;

import com.bank.cardservice.model.Card;
import com.bank.cardservice.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CardViewController {
    private final CardService cardService;

    @GetMapping("/view")
    private String getCards(@RequestParam(required = false) Long userId, Model model){
        List<Card> cards = cardService.getCardsByUserId(userId);
        model.addAttribute("cards", cards);
        model.addAttribute("ownerName", cardService.getOwnerFullName(userId).toUpperCase());
        return "card";
    }
}
