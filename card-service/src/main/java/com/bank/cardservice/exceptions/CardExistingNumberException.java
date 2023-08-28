package com.bank.cardservice.exceptions;

public class CardExistingNumberException extends RuntimeException{
    public CardExistingNumberException(String message){
        super(message);
    }
    public CardExistingNumberException(String message,Throwable cause){
        super(message,cause);
    }
}
