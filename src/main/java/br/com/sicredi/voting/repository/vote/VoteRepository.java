package br.com.sicredi.voting.repository.vote;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sicredi.voting.domain.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    
}
