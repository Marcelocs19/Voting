package br.com.sicredi.voting.repository.session;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sicredi.voting.domain.Session;
import br.com.sicredi.voting.domain.enums.Status;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    
    List<Session> findByStatus(Status status);

}
