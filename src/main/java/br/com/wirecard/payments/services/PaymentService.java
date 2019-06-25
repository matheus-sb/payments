package br.com.wirecard.payments.services;

import br.com.wirecard.payments.models.CreditCardPayment;
import br.com.wirecard.payments.models.Payment;
import br.com.wirecard.payments.repositories.PaymentRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public abstract class PaymentService<T extends Payment> {

    private final PaymentRepository repository;

    private final ClientService clientService;

    public T create(T payment) {
        validate(payment);
        setPaymentToBuyer(payment);
        processPayment(payment);
        
        return repository.save(payment);
    }

    protected abstract void processPayment(T payment);

    private void setPaymentToBuyer(T payment) {
        payment.getBuyer().setPayment(payment);
    }
    
    private void validate(T payment) {
        clientService.checkIfClientExistsById(payment.getClient().getId());
        validatePayment(payment);
    }

    protected abstract void validatePayment(T payment);

    public List<Payment> findAll() {
        return repository.findAll();
    }
}
