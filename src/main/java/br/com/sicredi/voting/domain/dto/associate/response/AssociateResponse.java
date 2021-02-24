package br.com.sicredi.voting.domain.dto.associate.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssociateResponse {
    
    private Long associateId;

    private String cpf;
}
