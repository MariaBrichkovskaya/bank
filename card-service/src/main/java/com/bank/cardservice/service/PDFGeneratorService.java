package com.bank.cardservice.service;

import com.bank.cardservice.dto.OperationDTO;
import com.bank.cardservice.model.Operation;
import com.lowagie.text.Document;

public interface PDFGeneratorService {
    void exportOperationInfoInPDF(Operation operation);
}
