package com.bank.cardservice.service;

import java.math.BigDecimal;

public interface BalanceService {
    void moneyTransfer(String from, BigDecimal sum);
    void moneyReceive(String to,BigDecimal sum);
}
