package br.com.wirecard.payments.services;

import br.com.wirecard.payments.exceptions.ClientNotFoundException;
import br.com.wirecard.payments.exceptions.CreditCardException;
import br.com.wirecard.payments.exceptions.InvalidCreditCardException;
import br.com.wirecard.payments.mocks.CreditCardAdministratorMock;
import br.com.wirecard.payments.models.Buyer;
import br.com.wirecard.payments.models.Client;
import br.com.wirecard.payments.models.CreditCardPayment;
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
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class CreditCardPaymentServiceTest {

    @Mock
    private ClientService clientService;

    @Mock
    private CreditCardAdministratorMock creditCardAdministratorMock;

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private CreditCardPaymentService paymentService;

    @Test
    public void createCreditCardPayment() {
        when(creditCardAdministratorMock.processCreditCard(any(CreditCardPayment.class))).thenReturn(true);
        when(paymentRepository.save(any(Payment.class)))
                .thenAnswer(i -> paymentDbMock(i.getArgument(0, Payment.class)));

        CreditCardPayment creditCardPayment = paymentService.create(payment());

        assertThat(creditCardPayment.getId()).isEqualTo(77L);
    }

    @Test
    public void creditCardPaymentInvalidCreditCardException() {
        CreditCardPayment creditCard = payment();
        creditCard.setCardNumber("111122211177777");

        assertThrows(InvalidCreditCardException.class, () -> {
            paymentService.create(creditCard);
        });
    }

    @Test
    public void creditCardPaymentCreditCardException() {
        when(creditCardAdministratorMock.processCreditCard(any(CreditCardPayment.class))).thenReturn(false);

        assertThrows(CreditCardException.class, () -> {
            paymentService.create(payment());
        });
    }

    @Test
    public void creditCardPaymentClientNotFoundException() {
        when(clientService.checkIfClientExistsById(any(Long.class))).thenThrow(ClientNotFoundException.class);

        assertThrows(ClientNotFoundException.class, () -> {
            paymentService.create(payment());
        });
    }

    @Test
    public void findAllCreditCardPayments() {
        when(paymentRepository.findAll()).thenReturn(Arrays.asList(
                CreditCardPayment.builder().build(),
                CreditCardPayment.builder().build()));

        List<Payment> payments = paymentService.findAll();

        assertEquals(2, payments.size());
    }

    private CreditCardPayment payment() {
        return CreditCardPayment.builder()
                .client(client())
                .buyer(buyer())
                .cardHolderName("Testando")
                .cardNumber("5344618092971798")
                .cardExpirationDate("03/21")
                .cardCCV("214")
                .amount(BigDecimal.valueOf(50))
                .build();
    }

    private Buyer buyer() {
        return Buyer.builder()
                .name("teste")
                .email("teste@teste.com")
                .cpf("81781902143")
                .build();
    }

    private Client client() {
        return Client.builder()
                .id(1L)
                .build();
    }

    private Payment paymentDbMock(Payment payment) {
        CreditCardPayment creditCard = (CreditCardPayment) payment;

        return CreditCardPayment.builder()
                .id(77L)
                .client(creditCard.getClient())
                .buyer(creditCard.getBuyer())
                .cardHolderName(creditCard.getCardHolderName())
                .cardNumber(creditCard.getCardNumber())
                .cardExpirationDate(creditCard.getCardExpirationDate())
                .cardCCV(creditCard.getCardCCV())
                .amount(creditCard.getAmount())
                .build();
    }
}