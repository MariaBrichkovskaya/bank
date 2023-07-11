package com.bank.cardservice.service.impl;

import com.bank.cardservice.dto.TransferDTO;
import com.bank.cardservice.model.Card;
import com.bank.cardservice.repository.CardRepository;
import com.bank.cardservice.service.BalanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
@Slf4j
public class BalanceServiceImp implements BalanceService{
    private final CardRepository cardRepository;
    @Override
    public void moneyTransfer(String from, BigDecimal sum) {
        Card userFrom=cardRepository.findByNumber(from);
        BigDecimal newBalance=userFrom.getBalance().subtract(sum);
        userFrom.setBalance(newBalance);
        log.info("Transfer from {} {}",from,sum);
    }

    @Override
    public void moneyReceive(String to, BigDecimal sum) {
        Card userTo=cardRepository.findByNumber(to);
        BigDecimal newBalance=userTo.getBalance().add(sum);
        userTo.setBalance(newBalance);
        log.info("Transfer to {} {}",to,sum);

    }

    @Transactional
    public void transferOperation(TransferDTO transfer) {
        moneyTransfer(transfer.getFrom(),transfer.getSum());
        moneyReceive(transfer.getTo(),transfer.getSum());
    }
}
