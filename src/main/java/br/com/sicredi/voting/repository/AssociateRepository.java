package br.com.sicredi.voting.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sicredi.voting.domain.Associate;

@Repository
public interface AssociateRepository extends JpaRepository<Associate, Long> {
    
    Optional<Associate> findByCpf(String cpf);

}
