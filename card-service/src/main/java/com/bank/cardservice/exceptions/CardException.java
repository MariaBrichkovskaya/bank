package com.bank.cardservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@AllArgsConstructor
@Getter
@Setter
public class CardException {
    private final String message;
    private final Throwable throwable;
    private final HttpStatus httpStatus;
}
