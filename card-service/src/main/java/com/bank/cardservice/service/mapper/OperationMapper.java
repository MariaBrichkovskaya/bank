package com.bank.cardservice.service.mapper;

import com.bank.cardservice.dto.OperationDTO;
import com.bank.cardservice.model.Operation;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.function.Function;

@Service
public class OperationMapper implements Function<Operation, OperationDTO> {


    @Override
    public OperationDTO apply(Operation operation) {
        return OperationDTO.builder()
                .id(operation.getId())
                .sum(operation.getSum())
                .date(operation.getDate())
                .time(operation.getTime())
                .type(operation.getType())
                .cardNumber(operation.getCardNumber())
                .commission(operation.getSum().multiply(BigDecimal.valueOf(0.02)))
                .build();
    }
}
