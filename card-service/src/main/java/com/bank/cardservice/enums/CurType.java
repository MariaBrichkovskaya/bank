package com.bank.cardservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CurType {
    BYN("0"),
    USD("431"),
    EUR("451"),
    RUB("456"),
    PLN("452"),
    CNY("462");

    private final String id;
}
