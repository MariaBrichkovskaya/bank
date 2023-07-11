package com.bank.cardservice.controller;

import com.bank.cardservice.dto.TransferDTO;
import com.bank.cardservice.service.impl.BalanceServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping
public class BalanceController {
    private final BalanceServiceImp balanceService;
    @PostMapping("/transfer")
    private void transfer(TransferDTO transfer){
        //транзакция
        balanceService.transferOperation(transfer);
    }

}
