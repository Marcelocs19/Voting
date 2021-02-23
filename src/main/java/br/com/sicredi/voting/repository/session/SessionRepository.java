package br.com.sicredi.voting.repository.session;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.sicredi.voting.domain.Session;
import br.com.sicredi.voting.domain.dto.session.response.SessionResponse;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    
    @Query(value = "SELECT new br.com.sicredi.voting.domain.dto.session.response.SessionResponse(s.sessionId, s.meetingDate, sch, s.duration, s.status) FROM Session s JOIN FETCH s.schedule sch")
    List<SessionResponse> findAllSessionsOpen();
}
