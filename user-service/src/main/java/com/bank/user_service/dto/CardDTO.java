package com.bank.user_service.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

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
    Float balance;
}
