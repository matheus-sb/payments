package br.com.wirecard.payments.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("BOLETO")
@Getter
@SuperBuilder
@NoArgsConstructor
public class BoletoPayment extends Payment {

    @Setter
    @Column(name = "numero_boleto")
    private String number;
}
