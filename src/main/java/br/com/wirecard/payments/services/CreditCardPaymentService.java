package br.com.wirecard.payments.services;

import br.com.moip.validators.CreditCard;
import br.com.wirecard.payments.exceptions.CreditCardException;
import br.com.wirecard.payments.exceptions.InvalidCreditCardException;
import br.com.wirecard.payments.mocks.CreditCardAdministratorMock;
import br.com.wirecard.payments.models.CreditCardPayment;
import br.com.wirecard.payments.repositories.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class CreditCardPaymentService extends PaymentService<CreditCardPayment> {

    private final CreditCardAdministratorMock creditCardAdministratorMock;

    public CreditCardPaymentService(PaymentRepository repository, ClientService clientService,
                                    CreditCardAdministratorMock creditCardAdministratorMock) {
        super(repository, clientService);
        this.creditCardAdministratorMock = creditCardAdministratorMock;
    }

    @Override
    protected void processPayment(CreditCardPayment creditCard) {
        if (!creditCardAdministratorMock.processCreditCard(creditCard)){
            throw new CreditCardException("Pagamento não autorizado pela administradora do cartão");
        }
    }

    @Override
    protected void validatePayment(CreditCardPayment payment) {
        CreditCard creditCard = new CreditCard(payment.getCardNumber());

        if (!creditCard.isValid()) {
            throw new InvalidCreditCardException("Número do cartão inválido ou bandeira desconhecida");
        }
    }
}
