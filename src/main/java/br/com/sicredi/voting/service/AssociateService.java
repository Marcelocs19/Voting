package br.com.sicredi.voting.service;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.com.sicredi.voting.domain.Associate;
import br.com.sicredi.voting.domain.dto.associate.request.AssociateRequest;
import br.com.sicredi.voting.domain.dto.associate.response.AssociateResponse;
import br.com.sicredi.voting.repository.AssociateRepository;
import br.com.sicredi.voting.validation.Message;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Validated
@Slf4j
public class AssociateService {

    private AssociateRepository repository;

	public AssociateResponse insertAssociate(@Valid AssociateRequest request) {
        repository.findByCpf(request.getCpf()).ifPresent(a -> {throw Message.BAD_REQUEST_ASSOCIATE.asBusinessException();});
        
        var associate = repository.save(Associate.of(request));
        log.info("method = insertAssociate associateId = {}", associate.getAssociateId());
		return associate.toDto();
	}
    
}
