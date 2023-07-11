package com.bank.cardservice.controller;

import com.bank.cardservice.service.impl.BalanceServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Controller
@RequestMapping("/balance")
public class BalanceController {
    private final BalanceServiceImp balanceService;
    @PostMapping() //я жестко хз какой тут адрес придумай пж у меня стресс
    private void transfer(@RequestParam String from,@RequestParam String to,@RequestParam BigDecimal sum){
        //транзакция
        balanceService.moneyTransfer(from,sum);
        balanceService.moneyReceive(to,sum);
    }

}
