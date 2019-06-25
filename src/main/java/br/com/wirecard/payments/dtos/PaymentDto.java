package br.com.wirecard.payments.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeInfo(
        use = NAME,
        include = PROPERTY,
        property = "type")
@JsonSubTypes({
        @Type(value = BoletoPaymentDto.class, name = "BOLETO"),
        @Type(value = CreditCardPaymentDto.class, name = "CARTAO_CREDITO")
})
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class PaymentDto {

    private Long id;

    @NotNull
    private ClientDto client;

    @NotNull
    private BuyerDto buyer;

    @Positive
    @NotNull
    private BigDecimal amount;

    @JsonIgnore
    public boolean isBoletoPayment() {
        return this instanceof BoletoPaymentDto;
    }
}
