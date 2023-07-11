package com.bank.cardservice.service;

import com.bank.cardservice.exceptions.TransferException;

import java.math.BigDecimal;

public interface BalanceService {
    void moneyTransfer(String from, BigDecimal sum) throws TransferException;
    void moneyReceive(String to,BigDecimal sum);
}
