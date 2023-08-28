package com.bank.cardservice.controller;

import com.bank.cardservice.dto.TransferDTO;
import com.bank.cardservice.service.BalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping
public class BalanceController {
    private final BalanceService balanceService;
    @PostMapping("/transfer")
    private ResponseEntity<String> transfer(TransferDTO transfer){
        if (balanceService.transferOperation(transfer)) return ResponseEntity.ok().body("Money transfer was successful");
        return ResponseEntity.badRequest().body("Money transfer error");
    }

}
