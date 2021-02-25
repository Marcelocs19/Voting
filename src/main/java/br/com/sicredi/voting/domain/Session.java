package br.com.sicredi.voting.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
@EqualsAndHashCode(of = "sessionId", callSuper = false)
@Setter
@Getter
@Builder
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
    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Schedule> schedules;

    @Column(name = "DURACAO", nullable = false)
    private Long duration;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false)
    private Status status;

    public static Session of(SessionRequest request, List<Schedule> schedule) {
        Session session = Session.builder().duration((request.getDuration() == null) ? 1L : request.getDuration())
                .meetingDate(LocalDateTime.now()).status(Status.OPEN).schedules(new ArrayList<>()).build();
        session.addSchedule(schedule);
        return session;
    }

    public void addSchedule(List<Schedule> schedule) {
        schedule.forEach(s -> {
            s.setSession(this);
            this.schedules.add(s);
        });
    }

    public SessionResponse toDto() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return SessionResponse.builder().sessionId(this.sessionId).meetingDate(formatter.format(this.meetingDate))
                .schedule(this.schedules).duration(this.duration).status(this.status).build();
    }

    public boolean checkSchedule() {
        boolean check = true;
        LocalDateTime plusMinutes = this.meetingDate.plusMinutes(this.duration);
        if (plusMinutes.isBefore(LocalDateTime.now())) {
            this.status = Status.CLOSE;
            check = false;
        }
        return check;
    }

    public static List<Session> checkListSessions(List<Session> sessions) {
        sessions.forEach(Session::checkSchedule);
        return sessions;
    }

}
