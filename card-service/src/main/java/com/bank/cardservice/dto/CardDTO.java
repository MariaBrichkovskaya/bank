package com.bank.cardservice.dto;

import com.bank.cardservice.enums.TypeEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardDTO {
    Long id;
    String number;
    LocalDate issueDate;
    LocalDate expiryDate;
    Long clientId;
    Boolean locked;
    BigDecimal balance;
    TypeEnum typeEnum;
}
