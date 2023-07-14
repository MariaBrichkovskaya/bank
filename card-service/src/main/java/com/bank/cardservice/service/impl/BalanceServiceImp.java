package com.bank.cardservice.service.impl;

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
import org.springframework.web.reactive.function.client.WebClient;
import reactor.util.retry.Retry;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;

@RequiredArgsConstructor
@Service
@Slf4j
public class BalanceServiceImp implements BalanceService{
    private final CardRepository cardRepository;
    private final OperationServiceImp operationService;
    private final PDFGeneratorServiceImp pdfGeneratorService;
    private final MailSenderServiceImpl mailSenderService;
    private final BigDecimal COMMISSION= BigDecimal.valueOf(0.02);
    private static final String INFO_MODULE_URI = "http://localhost:9090/cur/";
    private final WebClient webClient;
    BigDecimal commission= BigDecimal.valueOf(0);
    void validation(Card userFrom,Card userTo,BigDecimal sum) throws TransferException {
        if (sum.compareTo(userFrom.getBalance()) > 0)
            throw new TransferException("Недостаточно средств");
        if (sum.compareTo(BigDecimal.valueOf(0))<1)
            throw new TransferException("Некорректная сумма перевода");
        if (userFrom.getLocked().equals(true)||userTo.getLocked().equals(true))
            throw new TransferException("Карта, с которой осуществляется перевод, заблокирована");

    }
    private Double getCoefficient(Card user){

            String client= webClient.get()
                    .uri(INFO_MODULE_URI + user.getCurrency().getId())
                    .retrieve()
                    .bodyToMono(String.class)
                    .retryWhen(Retry.fixedDelay(3, Duration.ofMillis(100)))
                    .block();
            return Double.parseDouble(client);
    }
    private BigDecimal getSum(Card userFrom, Card userTo, BigDecimal sum){
        if (!userFrom.getCurrency().equals(userTo.getCurrency()))
        {
            return (!userFrom.getCurrency().getId().equals("0")?
                    sum.multiply(BigDecimal.valueOf(getCoefficient(userFrom))):
                    sum.divide(BigDecimal.valueOf(getCoefficient(userTo)),4, RoundingMode.HALF_UP));
        }
        return sum;

    }
    @Override
    public void moneyTransfer(String from, BigDecimal sum,String to) throws TransferException, MessagingException, FileNotFoundException {

        Card userFrom=cardRepository.findByNumber(from);
        Card userTo=cardRepository.findByNumber(to);
        validation(userFrom,userTo,sum);
        if (!userTo.getType().equals(userFrom.getType()))
        {
            commission=sum.multiply(COMMISSION);
            log.info("commission {}",commission);
        }

        Operation operation=getTransferOperation(userFrom,sum,from);
        operationService.commitOperation(operation);
        pdfGeneratorService.exportOperationInfoInPDF(operation);
        mailSenderService.sendEmailWithAttachment("dima427614@gmail.com", "Чек о переводе денег", "Ваш чек", "./card-service/src/main/resources/operation-checks/Operation" + operation.getId() + ".pdf");
        log.info("Transfer from {} {} rub",from,sum);
    }
    private Operation getTransferOperation(Card userFrom,BigDecimal sum,String from){
        BigDecimal newBalance=userFrom.getBalance().subtract(sum).subtract(commission);
        userFrom.setBalance(newBalance);
        return Operation.builder()
                .cardNumber(from)
                .sum(sum)
                .type(OperationType.TRANSFER)
                .build();
    }
    private Operation getReceiveOperation(Card userTo,BigDecimal sum,String to){
        BigDecimal newBalance=userTo.getBalance().add(sum);
        userTo.setBalance(newBalance);
        return Operation.builder()
                .cardNumber(to)
                .sum(sum)
                .type(OperationType.RECEIVE)
                .build();
    }

    @Override
    public void moneyReceive(String to, BigDecimal sum) throws TransferException {
        Card userTo=cardRepository.findByNumber(to);
        if (userTo.getLocked().equals(true))
            throw new TransferException("Карта, на которую осуществляется перевод, заблокирована");
        Operation operation=getReceiveOperation(userTo,sum,to);
        operationService.commitOperation(operation);
        log.info("Transfer to {} {} rub",to,sum);
    }

    @Transactional
    public boolean transferOperation(TransferDTO transfer){
        try {
            if (transfer.getFrom().equals(transfer.getTo()))
                throw new TransferException("Как можно переводить себе же");
            BigDecimal sum= getSum(cardRepository.findByNumber(transfer.getFrom()),
                    cardRepository.findByNumber(transfer.getTo()),transfer.getSum());
            moneyTransfer(transfer.getFrom(),transfer.getSum(),transfer.getTo());
            moneyReceive(transfer.getTo(),sum);
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
