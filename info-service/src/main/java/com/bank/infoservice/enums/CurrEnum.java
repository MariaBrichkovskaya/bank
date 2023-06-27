package com.bank.infoservice.enums;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public enum CurrEnum {
    USD("431"),
    EUR("451"),
    RUB("456"),
    PLN("452"),
    CNY("462");

    private String id;
    public String toJsonString() {
        return new Gson().toJson(this);
    }

}
