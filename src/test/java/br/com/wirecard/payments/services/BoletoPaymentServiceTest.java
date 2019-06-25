package br.com.wirecard.payments.services;

import br.com.wirecard.payments.exceptions.ClientNotFoundException;
import br.com.wirecard.payments.mocks.BoletoGeneratorMock;
import br.com.wirecard.payments.models.BoletoPayment;
import br.com.wirecard.payments.models.Buyer;
import br.com.wirecard.payments.models.Client;
import br.com.wirecard.payments.models.Payment;
import br.com.wirecard.payments.repositories.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class BoletoPaymentServiceTest {

    @Mock
    private ClientService clientService;

    @Mock
    private BoletoGeneratorMock boletoGeneratorMock;

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private BoletoPaymentService paymentService;

    private final static String BOLETO_NUMBER = "23790504004199062421941008109203779330000019900";

    @Test
    public void createBoletoPayment() {
        when(boletoGeneratorMock.generateBoleto(any(BoletoPayment.class)))
                .thenReturn(BOLETO_NUMBER);
        when(paymentRepository.save(any(Payment.class))).thenAnswer(returnsFirstArg());

        BoletoPayment boleto = paymentService.create(payment());

        assertThat(boleto.getNumber()).isEqualTo(BOLETO_NUMBER);
    }

    @Test
    public void boletoPaymentClientNotFoundException() {
        when(clientService.checkIfClientExistsById(any(Long.class))).thenThrow(ClientNotFoundException.class);

        assertThrows(ClientNotFoundException.class, () -> {
            paymentService.create(payment());
        });
    }

    @Test
    public void findAllBoletoPayments() {
        when(paymentRepository.findAll()).thenReturn(Arrays.asList(
                BoletoPayment.builder().build(),
                BoletoPayment.builder().build()));

        List<Payment> payments = paymentService.findAll();

        assertEquals(2, payments.size());
    }

    private BoletoPayment payment() {
        return BoletoPayment.builder()
                .client(client())
                .buyer(buyer())
                .amount(BigDecimal.valueOf(60))
                .build();
    }

    private Buyer buyer() {
        return Buyer.builder()
                .name("testando")
                .email("testando@teste.com")
                .cpf("71284769380")
                .build();
    }

    private Client client() {
        return Client.builder()
                .id(1L)
                .build();
    }
}