package br.com.wirecard.payments.controllers;

import br.com.wirecard.payments.dtos.PaymentDto;
import br.com.wirecard.payments.mappers.PaymentMapper;
import br.com.wirecard.payments.models.BoletoPayment;
import br.com.wirecard.payments.models.CreditCardPayment;
import br.com.wirecard.payments.models.Payment;
import br.com.wirecard.payments.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService<BoletoPayment> boletoPaymentService;
    
    private final PaymentService<CreditCardPayment> creditCardPaymentService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody PaymentDto dto) {
        PaymentMapper mapper = new PaymentMapper();
        Payment payment = mapper.convertFromDtoToObject(dto);
        
        if (payment.isBoletoPayment()) {
            BoletoPayment boleto = boletoPaymentService.create((BoletoPayment) payment);
            return ResponseEntity.status(CREATED).body(boleto.getNumber());
        }

        creditCardPaymentService.create((CreditCardPayment) payment);
        return ResponseEntity.status(CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<PaymentDto>> findAll() {
        List<Payment> payments = boletoPaymentService.findAll();
        List<PaymentDto> paymentDtos = new PaymentMapper().convertFromObjectsToDtos(payments);
        return ResponseEntity.ok(paymentDtos);
    }
}
