package br.com.wirecard.payments.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
public class CreditCardException extends RuntimeException {

    public CreditCardException(String message) {
        super(message);
    }
}
