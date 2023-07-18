package com.bank.cardservice.service.impl;


import com.bank.cardservice.model.Operation;
import com.bank.cardservice.service.PDFGeneratorService;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;



@Service
@Slf4j
@RequiredArgsConstructor
public class PDFGeneratorServiceImp implements PDFGeneratorService {

    @Override
    public void exportOperationInfoInPDF(Operation operation) {
        try (Document document = new Document(PageSize.A4)) {
            PdfWriter.getInstance(document, new FileOutputStream("./card-service/src/main/resources/operation-checks/Operation" + operation.getId() + ".pdf"));
            document.open();

            Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE, 25);
            Paragraph title = new Paragraph("Money Bank", fontTitle);
            title.setAlignment(Paragraph.ALIGN_RIGHT);

            //Font fontContent = FontFactory.getFont(FontFactory.COURIER, 20);
            Paragraph date = new Paragraph("Дата " +operation.getDate().toString());
            Paragraph time = new Paragraph("Время " +operation.getTime());
            Paragraph card = new Paragraph("Карта для операции " +operation.getCardNumber());
            Paragraph sum = new Paragraph("Сумма перевода " +operation.getSum());
            Paragraph commission = new Paragraph("Комиссия " +operation.getSum());
            Paragraph fullSum = new Paragraph("Сумма к оплате " +operation.getSum());
            Paragraph checkId = new Paragraph("Номер чека " +operation.getId());
            document.add(title);
            document.add(date);
            document.add(time);
            document.add(card);
            document.add(sum);
            document.add(commission);
            document.add(fullSum);
            document.add(checkId);
            document.close();
            log.info("Generate users pdf report.");
        } catch (IOException e) {
            log.error("Error exporting users. {}", e.getMessage());
            e.printStackTrace();
        }
    }
}
