package br.com.wirecard.payments.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.InheritanceType.SINGLE_TABLE;

@Entity
@Inheritance(strategy = SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_pagamento")
@Table(name = "meios_pagamento")
@Getter
@SuperBuilder
@NoArgsConstructor
public abstract class Payment {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "valor")
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Client client;

    @OneToOne(mappedBy = "payment", cascade = ALL, optional = false)
    private Buyer buyer;

    public boolean isBoletoPayment() {
        return this instanceof BoletoPayment;
    }

    public boolean isCreditCardPayment() {
        return this instanceof CreditCardPayment;
    }
}



