package br.com.wirecard.payments.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "compradores")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Buyer {

    @Id
    private Long id;

    @Column(name = "nome", nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, length = 11)
    private String cpf;

    @OneToOne
    @MapsId
    @Setter
    private Payment payment;
}
