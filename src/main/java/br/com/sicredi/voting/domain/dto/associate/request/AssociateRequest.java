package br.com.sicredi.voting.domain.dto.associate.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AssociateRequest {

    @NotBlank(message = "O campo cpf é obrigatório")
    @Size(message = "O tamanho do campo cpf é inválido", min = 11, max = 11)
    @CPF(message = "O campo cpf está incorreto")
    private String cpf;

}
