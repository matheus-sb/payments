package br.com.wirecard.payments.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;

import static com.fasterxml.jackson.annotation.JsonInclude.*;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardPaymentDto extends PaymentDto{

    @NotBlank
    private String cardHolderName;

    @NotBlank
    private String cardNumber;

    @NotBlank
    private String cardExpirationDate;

    @NotBlank
    @JsonInclude(Include.NON_NULL)
    private String cardCCV;
}
