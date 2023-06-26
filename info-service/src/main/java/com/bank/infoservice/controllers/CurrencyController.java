package com.bank.infoservice.controllers;

import com.bank.infoservice.dto.CurrencyDTO;
import com.bank.infoservice.services.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.ParseException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/currency")
public class CurrencyController {
    private final CurrencyService currencyService;

    @GetMapping("/{message}")
    public ResponseEntity<CurrencyDTO> getCurrency(@PathVariable String message) throws IOException, ParseException {

        return ResponseEntity.ok(currencyService.getCurrencyRate(message));  //поиск по id в процессе переделать на выбор валют из списка
    }

}
