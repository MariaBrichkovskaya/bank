package com.bank.infoservice.controllers;

import com.bank.infoservice.dto.CurrencyDTO;
import com.bank.infoservice.enums.CurrEnum;
import com.bank.infoservice.services.CurrencyService;
import jakarta.ws.rs.QueryParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
//@RequestMapping("/currency")
public class CurrencyController {
    //private final CurrencyService currencyService;

    @GetMapping
    public String getCurrency( @RequestParam (required = false)String message, Model model) throws IOException, ParseException {
        CurrencyDTO currency = CurrencyService.getCurrencyRate(message);
        List<CurrEnum> currencies = List.of(CurrEnum.values());

        System.err.println(message);
        model.addAttribute("currency", currency);
        model.addAttribute("currencies", currencies);
        model.addAttribute("message", message);

        return "info";
        //return ResponseEntity.ok(currencyService.getCurrencyRate(message));  //поиск по id в процессе переделать на выбор валют из списка
    }
}