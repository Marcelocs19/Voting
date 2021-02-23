package br.com.sicredi.voting.domain.dto.vote.request;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class VoteRequest {

    @NotNull(message = "O campo scheduleId é obrigatório")
    private Long scheduleId;

    @NotNull(message = "O campo sessionId é obrigatório")
    private Long sessionId;
        
    private String cpf;
    
}
