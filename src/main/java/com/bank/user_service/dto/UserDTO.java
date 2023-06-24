package com.bank.user_service.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    String name;
    String surname;
    String email;
    String password;
}
