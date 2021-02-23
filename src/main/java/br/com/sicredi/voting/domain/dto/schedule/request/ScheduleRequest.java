package br.com.sicredi.voting.domain.dto.schedule.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ScheduleRequest {

    @NotBlank(message = "O campo title é obrigatório")
    @Size(message = "O tamanho do campo title é inválido", min = 5, max = 50)
    private String title;

    @NotBlank(message = "O campo subject é obrigatório")
    @Size(message = "O tamanho do campo subject é inválido", min = 5, max = 100)
    private String subject;
    
}
