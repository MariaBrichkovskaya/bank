package com.bank.cardservice.service;

import com.bank.cardservice.dto.TransferDTO;
import com.bank.cardservice.exceptions.TransferException;
import jakarta.mail.MessagingException;

import java.io.FileNotFoundException;
import java.math.BigDecimal;

public interface BalanceService {
    void moneyTransfer(String from, BigDecimal sum,String to) throws TransferException, MessagingException, FileNotFoundException;
    void moneyReceive(String to,BigDecimal sum) throws TransferException;

    boolean transferOperation(TransferDTO transfer);
}
