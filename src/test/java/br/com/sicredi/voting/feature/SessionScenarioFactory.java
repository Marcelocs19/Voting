package br.com.sicredi.voting.feature;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.sicredi.voting.domain.Schedule;
import br.com.sicredi.voting.domain.Session;
import br.com.sicredi.voting.domain.dto.session.request.SessionRequest;
import br.com.sicredi.voting.domain.dto.session.response.SessionResponse;
import br.com.sicredi.voting.domain.enums.Status;

public class SessionScenarioFactory {

    public static final Session SESSION = loadSession();
    public static final Session SESSION_NEW = loadNewSession();
    public static final Session SESSION_BUILDER = loadSessionBuilder();
    public static final Session SESSION_SET = loadSessionSet();
    public static final Session SESSION_GET = loadSessionGet();
    public static final SessionResponse SESSION_RESPONSE = loadSessionResponse();
    public static final Session SESSION_OF = loadScheduleOf();    

    private static Session loadSession() {
        List<Schedule> list = new ArrayList<>();
        list.add(ScheduleScenarioFactory.SCHEDULE);
        return new Session(1L, LocalDateTime.now(), list, 5L, Status.OPEN);
    }

    private static Session loadNewSession() {
        List<Schedule> list = new ArrayList<>();
        list.add(ScheduleScenarioFactory.SCHEDULE_NEW);
        return new Session(1L, LocalDateTime.now(), list, 5L, Status.OPEN);
    }

    private static Session loadSessionBuilder() {
        List<Schedule> list = new ArrayList<>();
        list.add(ScheduleScenarioFactory.SCHEDULE);
        return Session.builder()
        .sessionId(1L)
        .meetingDate(LocalDateTime.now())
        .schedules(list)
        .duration(3L)
        .status(Status.OPEN)
        .build();
    }
    
    private static Session loadSessionSet() {
        List<Schedule> list = new ArrayList<>();
        list.add(ScheduleScenarioFactory.SCHEDULE);
        Session session = new Session();
        session.setSessionId(1L);
        session.setMeetingDate(LocalDateTime.now());
        session.setSchedules(list);
        session.setDuration(4L);
        session.setStatus(Status.OPEN);
        return session;
    }

    private static Session loadSessionGet() {
        Session session = new Session();
        session.getSessionId();
        session.getMeetingDate();
        session.getSchedules();
        session.getDuration();
        session.getStatus();
        return session;
    }

    private static SessionResponse loadSessionResponse() {
        Session session = loadSession();
        return session.toDto();        
    }

    private static Session loadScheduleOf() {
        SessionRequest request = SessionRequest.builder().build();
        List<Schedule> list = new ArrayList<>();
        list.add(ScheduleScenarioFactory.SCHEDULE);
        return Session.of(request, list);
    }

}
