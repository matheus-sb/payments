package br.com.wirecard.payments.models;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CARTAO_CREDITO")
@Data
@SuperBuilder
@NoArgsConstructor
public class CreditCardPayment extends Payment {

    @Column(name = "nome_cartao")
    private String cardHolderName;

    @Column(name = "numero_cartao")
    private String cardNumber;

    @Column(name = "data_expiracao_cartao")
    private String cardExpirationDate;

    @Column(name = "ccv_cartao")
    private String cardCCV;
}
