package br.com.sicredi.voting.domain.dto.schedule.response;

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
public class ScheduleResponse {
    
    private Long scheduleId;

    private String title;

    private String subject;
}
