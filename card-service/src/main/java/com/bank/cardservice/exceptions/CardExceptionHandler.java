package com.bank.cardservice.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CardExceptionHandler {
    @ExceptionHandler(value = {CardNotFoundException.class})
    public ResponseEntity<Object> handleCardNotFoundException(CardNotFoundException cardNotFoundException){
        CardException cardException=
                new CardException(
                cardNotFoundException.getMessage(),
                cardNotFoundException.getCause(), HttpStatus.NOT_FOUND
        );
        return new ResponseEntity<>(cardException,cardException.getHttpStatus());
    }

    @ExceptionHandler(value = {CardExistingNumberException.class})
    public ResponseEntity<Object> handleCardExistingNumberException(CardExistingNumberException cardExistingNumberException){
        CardException cardException= new CardException(
                cardExistingNumberException.getMessage(),
                cardExistingNumberException.getCause(), HttpStatus.CONFLICT
        );
        return new ResponseEntity<>(cardException,cardException.getHttpStatus());
    }
    @ExceptionHandler(value = {TransferException.class})
    public ResponseEntity<Object> handleTransferException(TransferException transferException){
        CardException cardException= new CardException(
                transferException.getMessage(),
                transferException.getCause(), HttpStatus.CONFLICT
        );
        return new ResponseEntity<>(cardException,cardException.getHttpStatus());
    }

}
