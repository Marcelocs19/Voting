package br.com.sicredi.voting.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.sicredi.voting.domain.dto.session.request.SessionRequest;
import br.com.sicredi.voting.domain.dto.session.response.SessionResponse;
import br.com.sicredi.voting.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of="sessionId", callSuper = false)
@Builder
@Setter
@Entity 
@Table(name = "TB_SESSAO")
public class Session {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CD_SESSAO", nullable = false)
	private Long sessionId;

    @Column(name = "DATA_SESSAO")
    private LocalDateTime meetingDate;
    
	@JsonIgnore
    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL, fetch = FetchType.EAGER)    
    private List<Schedule> schedule;

    @Column(name = "DURACAO", nullable = false)
    private Long duration;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false)
    private Status status;

    public static Session of(SessionRequest request) {        
        return Session.builder()
        .duration(request.getDuration())
        .meetingDate(request.getDate())
        .status(Status.OPEN)
        .schedule(new ArrayList<>())
        .build();
    }

    public void addSchedule(List<Schedule> schedule) {
        this.schedule.addAll(schedule);
    }

    public SessionResponse toDto() {
        return SessionResponse.builder()
        .sessionId(this.sessionId)
        .meetingDate(this.meetingDate)
        .schedule(this.schedule)
        .duration(this.duration)
        .status(this.status)
        .build();
    }
}
