package com.bank.infoservice.controllers;

import com.bank.infoservice.dto.CurrencyDTO;
import com.bank.infoservice.enums.CurrEnum;
import com.bank.infoservice.services.impl.CurrencyServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CurrencyController {
    private final CurrencyServiceImp currencyService;

    @GetMapping("/")
    public String getCurrency(@RequestParam(required = false) String message, Model model) throws IOException, ParseException {

        CurrencyDTO currency = currencyService.getCurrencyRate(message);
        List<CurrEnum> currencies= List.of(CurrEnum.values());
        model.addAttribute("currency",currency);
        model.addAttribute("currencies",currencies);
        model.addAttribute("message",message);
        return "info";
        //return ResponseEntity.ok(currencyService.getCurrencyRate(message));  //поиск по id в процессе переделать на выбор валют из списка
    }

    @GetMapping("/dynamic")
    public ResponseEntity<String> getDynamic(@RequestParam(required = false) String message){
        return ResponseEntity.ok(currencyService.getCurrencyDynamic(message).toString()) ;
    }
    @GetMapping("/currency")
    public ResponseEntity<String> getCurrencyInfo(@RequestParam(required = false) String message) throws ParseException {
        CurrencyDTO currencyDTO = currencyService.getCurrencyRate(message);
        return ResponseEntity.ok(
                currencyDTO.getCur_OfficialRate() + " BYN за "
                        + currencyDTO.getCur_Scale() + " "
                        + currencyDTO.getCur_Name());
    }
    @GetMapping("/cur/{currencyId}")
    public ResponseEntity<Double> getCurRate(@PathVariable String currencyId) throws ParseException {
        CurrencyDTO currencyDTO = currencyService.getCurrencyRate(currencyId);
        Double curPerUnit=currencyDTO.getCur_OfficialRate()/currencyDTO.getCur_Scale();
        return ResponseEntity.ok(curPerUnit);
    }

}
