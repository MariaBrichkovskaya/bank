package com.bank.cardservice.service.impl;

import com.bank.cardservice.dto.TransferDTO;
import com.bank.cardservice.exceptions.TransferException;
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
    private final BigDecimal COMMISSION= BigDecimal.valueOf(0.02);
    @Override
    public void moneyTransfer(String from, BigDecimal sum,String to) throws TransferException {

        Card userFrom=cardRepository.findByNumber(from);
        Card userTo=cardRepository.findByNumber(to);
        if (sum.compareTo(userFrom.getBalance()) > 0)
            throw new TransferException("Недостаточно средств");
        if (sum.compareTo(BigDecimal.valueOf(0))<1)
            throw new TransferException("Некорректная сумма перевода");
        if (userFrom.getLocked().equals(true))
            throw new TransferException("Карта, с которой осуществляется перевод, заблокирована");
        BigDecimal commission= BigDecimal.valueOf(0);
        if (!userTo.getType().equals(userFrom.getType()))
        {
            commission=sum.multiply(COMMISSION);
            log.info("commission {}",commission);
        }
        BigDecimal newBalance=userFrom.getBalance().subtract(sum).subtract(commission);
        userFrom.setBalance(newBalance);
        log.info("Transfer from {} {} rub",from,sum);
    }

    @Override
    public void moneyReceive(String to, BigDecimal sum) throws TransferException {
        Card userTo=cardRepository.findByNumber(to);
        if (userTo.getLocked().equals(true))
            throw new TransferException("Карта, на которую осуществляется перевод, заблокирована");
        BigDecimal newBalance=userTo.getBalance().add(sum);
        userTo.setBalance(newBalance);
        log.info("Transfer to {} {} rub",to,sum);

    }

    @Transactional
    public boolean transferOperation(TransferDTO transfer){
        try {
            if (transfer.getFrom().equals(transfer.getTo()))
                throw new TransferException("Как можно переводить себе же");
            moneyTransfer(transfer.getFrom(),transfer.getSum(),transfer.getTo());
            moneyReceive(transfer.getTo(),transfer.getSum());
        }catch (NullPointerException e){
            System.err.println("ноль даун");
            return false;
        }catch (TransferException t){
            System.err.println(t.getMessage());
            return false;
        }
        return true;
    }
}
