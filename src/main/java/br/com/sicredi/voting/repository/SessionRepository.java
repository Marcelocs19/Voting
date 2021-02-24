package br.com.sicredi.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sicredi.voting.domain.Session;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

}
