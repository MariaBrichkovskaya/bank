package com.bank.cardservice.service;

import com.bank.cardservice.dto.OperationDTO;
import com.bank.cardservice.model.Operation;

import java.util.List;

public interface OperationService {
    List<OperationDTO> getOperationsByCard(String card);
    void commitOperation(Operation operation);
}
