package com.bank.cardservice.exceptions;


public class TransferException extends Exception{
    public TransferException(String message) {
        super(message);
    }
    public TransferException(String message,Throwable cause) {
        super(message,cause);
    }
}
