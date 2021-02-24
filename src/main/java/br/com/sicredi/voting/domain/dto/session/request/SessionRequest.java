package br.com.sicredi.voting.domain.dto.session.request;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SessionRequest {

    @NotNull(message = "O campo duration é obrigatório")
    private Long duration;

    @NotNull(message = "O campo scheduleId é obrigatório")
    @Size(message = "É preciso pelo menos um scheduleId", min = 1)
    private List<Long> scheduleId;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @NotNull(message = "O campo dateSession é obrigatório")
    private LocalDateTime date;
    
}
