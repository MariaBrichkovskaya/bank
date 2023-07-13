package com.bank.cardservice.service.impl;

import com.bank.cardservice.dto.OperationDTO;
import com.bank.cardservice.dto.TransferDTO;
import com.bank.cardservice.enums.OperationType;
import com.bank.cardservice.exceptions.TransferException;
import com.bank.cardservice.model.Card;
import com.bank.cardservice.model.Operation;
import com.bank.cardservice.repository.CardRepository;
import com.bank.cardservice.service.BalanceService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
@Slf4j
public class BalanceServiceImp implements BalanceService{
    private final CardRepository cardRepository;
    private final OperationServiceImp operationService;
    private final PDFGeneratorServiceImp pdfGeneratorService;
    private final MailSenderServiceImpl mailSenderService;
    private final BigDecimal COMMISSION= BigDecimal.valueOf(0.02);
    @Override
    public void moneyTransfer(String from, BigDecimal sum,String to) throws TransferException, MessagingException, FileNotFoundException {

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
        Operation operation = Operation.builder()
                .cardNumber(from)
                .sum(sum)
                .type(OperationType.TRANSFER)
                .build();
        operationService.commitOperation(operation);
        pdfGeneratorService.exportOperationInfoInPDF(operation);
        mailSenderService.sendEmailWithAttachment("dima427614@gmail.com", "Чек о переводе денег", "Ваш чек", "./card-service/src/main/resources/operation-checks/Operation" + operation.getId() + ".pdf");
        log.info("Transfer from {} {} rub",from,sum);
    }

    @Override
    public void moneyReceive(String to, BigDecimal sum) throws TransferException {
        Card userTo=cardRepository.findByNumber(to);
        if (userTo.getLocked().equals(true))
            throw new TransferException("Карта, на которую осуществляется перевод, заблокирована");
        BigDecimal newBalance=userTo.getBalance().add(sum);
        userTo.setBalance(newBalance);
        Operation operation = Operation.builder()
                .cardNumber(to)
                .sum(sum)
                .type(OperationType.RECEIVE)
                .build();
        operationService.commitOperation(operation);
        log.info("Transfer to {} {} rub",to,sum);
    }

    @Transactional
    public boolean transferOperation(TransferDTO transfer){
        try {
            if (transfer.getFrom().equals(transfer.getTo()))
                throw new TransferException("Как можно переводить себе же");
            moneyTransfer(transfer.getFrom(),transfer.getSum(),transfer.getTo());
            moneyReceive(transfer.getTo(),transfer.getSum());
        }catch (NullPointerException | MessagingException | FileNotFoundException e){
            System.err.println("ноль даун");
            return false;
        }catch (TransferException t){
            System.err.println(t.getMessage());
            return false;
        }
        return true;
    }
}
