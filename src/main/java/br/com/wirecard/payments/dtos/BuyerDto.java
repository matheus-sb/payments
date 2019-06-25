package br.com.wirecard.payments.dtos;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
public class BuyerDto {

    private Long id;

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    @CPF
    @NotBlank
    private String cpf;

}
