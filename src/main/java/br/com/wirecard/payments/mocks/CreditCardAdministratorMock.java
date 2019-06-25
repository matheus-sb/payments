package br.com.wirecard.payments.mocks;

import br.com.wirecard.payments.models.CreditCardPayment;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CreditCardAdministratorMock {
    private final static BigDecimal VALUE = BigDecimal.valueOf(100);

    public Boolean processCreditCard(CreditCardPayment creditCard) {
        return creditCard.getAmount().compareTo(VALUE) <= 0;
    }
}
