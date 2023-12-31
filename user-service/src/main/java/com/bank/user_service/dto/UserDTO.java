package com.bank.user_service.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    Long id;
    String name;
    String surname;
    String email;
    String password;
    Set<CardDTO> cards;
}
