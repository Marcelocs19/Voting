package br.com.sicredi.voting.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.sicredi.voting.exception.BusinessException;
import br.com.sicredi.voting.feature.AssociateScenarioFactory;
import br.com.sicredi.voting.repository.AssociateRepository;

@RunWith(MockitoJUnitRunner.class)
public class AssocieteServiceTest {

    @InjectMocks
    AssociateService service;

    @Mock
    AssociateRepository repository;

    @Test
    public void insertAssociate_WhenVoteRequestIsValid_ExpectedOk() {     
        when(repository.findByCpf(any())).thenReturn(Optional.empty());
        when(repository.save(any())).thenReturn(AssociateScenarioFactory.ASSOCIATE);
        assertNotNull(service.insertAssociate(AssociateScenarioFactory.ASSOCIATE_REQUEST));
        verify(repository, times(1)).findByCpf(any());
        verify(repository, times(1)).save(any());
    }

    @Test(expected = BusinessException.class)
    public void insertAssociate_WhenVoteRequestIsNotValid_ExpectedBadRequest() {     
        when(repository.findByCpf(any())).thenReturn(Optional.of(AssociateScenarioFactory.ASSOCIATE));     
        assertNotNull(service.insertAssociate(AssociateScenarioFactory.ASSOCIATE_REQUEST));
        verify(repository, times(1)).findByCpf(any());
    }
    
}
