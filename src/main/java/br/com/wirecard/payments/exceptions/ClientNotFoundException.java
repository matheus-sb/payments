package br.com.wirecard.payments.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
public class ClientNotFoundException extends RuntimeException {

    private final static String MESSAGE = "Cliente não existe na base de dados";

    public ClientNotFoundException() {
        super(MESSAGE);
    }
}
