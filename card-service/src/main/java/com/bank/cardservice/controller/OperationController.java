package com.bank.cardservice.controller;

import com.bank.cardservice.dto.OperationDTO;
import com.bank.cardservice.service.impl.OperationServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class OperationController {
    private final OperationServiceImp operationService;
    @GetMapping("/operations")
    private ResponseEntity<List<OperationDTO>> getOperations(@RequestParam(name = "card", required = false) String cardNumber){
        List<OperationDTO> operations = operationService.getOperationsByCard(cardNumber);
        System.err.println(operations);
        System.err.println(cardNumber);
        return new ResponseEntity<>(operations, HttpStatus.OK);
    }
}
