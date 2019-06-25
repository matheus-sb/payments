package br.com.wirecard.payments.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
public class InvalidCreditCardException extends RuntimeException {

    public InvalidCreditCardException(String message) {
        super(message);
    }
}
