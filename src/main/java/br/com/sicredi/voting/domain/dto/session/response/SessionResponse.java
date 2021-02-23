package br.com.sicredi.voting.domain.dto.session.response;

import java.time.LocalDateTime;
import java.util.List;

import br.com.sicredi.voting.domain.Schedule;
import br.com.sicredi.voting.domain.enums.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class SessionResponse {

    private Long sessionId;

    private LocalDateTime meetingDate;

    private List<Schedule> schedule;

    private Long duration;

    private Status status;

    public SessionResponse() {
    }

    public SessionResponse(Long sessionId, LocalDateTime meetingDate, List<Schedule> schedule, Long duration,
            Status status) {
        this.sessionId = sessionId;
        this.meetingDate = meetingDate;
        this.schedule = schedule;
        this.duration = duration;
        this.status = status;
    }

}
