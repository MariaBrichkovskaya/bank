package com.bank.cardservice.service.impl;

import com.bank.cardservice.dto.OperationDTO;
import com.bank.cardservice.model.Operation;
import com.bank.cardservice.repository.OperationRepository;
import com.bank.cardservice.service.OperationService;
import com.bank.cardservice.service.mapper.OperationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Slf4j
@Service
public class OperationServiceImp implements OperationService {
    private final OperationRepository operationRepository;
    private final OperationMapper operationMapper;

    @Override
    public List<OperationDTO> getOperationsByCard(String card) {
        Stream<OperationDTO> operations = operationRepository
                .findByCardNumber(card)
                .stream()
                .map(operationMapper)
                .distinct();
        return operations.toList();
    }
    @Override
    public void commitOperation(Operation operation) {
        operationRepository.save(operation);
        log.info("Save operation. card: {}", operation.getCardNumber());
    }

}
