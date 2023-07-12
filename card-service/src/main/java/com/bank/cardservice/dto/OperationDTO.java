package com.bank.cardservice.dto;

import com.bank.cardservice.enums.OperationType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OperationDTO {
    long id;
    BigDecimal sum;
    LocalDate date;
    LocalTime time;
    OperationType type;
    String cardNumber;
    BigDecimal commission;
}
