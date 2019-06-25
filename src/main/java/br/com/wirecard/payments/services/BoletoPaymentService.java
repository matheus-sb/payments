package br.com.wirecard.payments.services;

import br.com.wirecard.payments.mocks.BoletoGeneratorMock;
import br.com.wirecard.payments.models.BoletoPayment;
import br.com.wirecard.payments.repositories.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class BoletoPaymentService extends PaymentService<BoletoPayment> {

    private final BoletoGeneratorMock boletoGenerator;

    public BoletoPaymentService(PaymentRepository repository, ClientService clientService,
                                BoletoGeneratorMock boletoGenerator) {
        super(repository, clientService);
        this.boletoGenerator = boletoGenerator;
    }

    @Override
    protected void processPayment(BoletoPayment boleto) {
        String boletoNumber = boletoGenerator.generateBoleto(boleto);
        boleto.setNumber(boletoNumber);
    }

    @Override
    protected void validatePayment(BoletoPayment payment) {

    }
}
