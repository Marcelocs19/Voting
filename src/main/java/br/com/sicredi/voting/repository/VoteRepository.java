package br.com.sicredi.voting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sicredi.voting.domain.Associate;
import br.com.sicredi.voting.domain.Schedule;
import br.com.sicredi.voting.domain.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    List<Vote> findByAssociateAndSchedule(Associate associate, Schedule schedule);
    
}
