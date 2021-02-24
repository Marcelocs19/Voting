package br.com.sicredi.voting.domain.dto.session.response;

import java.util.List;

import br.com.sicredi.voting.domain.Schedule;
import br.com.sicredi.voting.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class SessionResponse {

    private Long sessionId;

    private String meetingDate;

    private List<Schedule> schedule;

    private Long duration;

    private Status status;
   
}
