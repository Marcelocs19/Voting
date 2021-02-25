package br.com.sicredi.voting.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import br.com.sicredi.voting.feature.AssociateScenarioFactory;
import br.com.sicredi.voting.feature.SessionScenarioFactory;
import br.com.sicredi.voting.feature.VoteScenarioFactory;
import br.com.sicredi.voting.repository.AssociateRepository;
import br.com.sicredi.voting.repository.SessionRepository;
import br.com.sicredi.voting.repository.VoteRepository;

@RunWith(MockitoJUnitRunner.class)
public class VoteServiceTest {

    @InjectMocks
    VoteService service;

    @Mock
    VoteRepository voteRepository;

    @Mock
    AssociateRepository associateRepository;

    @Mock
    SessionRepository sessionRepository;

    @Mock
    UserInfoService userInfoService;

    @Test
    public void insertVote_WhenVoteRequestIsValid_ExpectedOk() {        
        when(sessionRepository.findById(any())).thenReturn(Optional.of(SessionScenarioFactory.SESSION));
        when(associateRepository.findByCpf(any())).thenReturn(Optional.of(AssociateScenarioFactory.ASSOCIATE));
        when(userInfoService.checkAssociateCanVote(any())).thenReturn(true);
        when(voteRepository.findByAssociateAndSchedule(any(),any())).thenReturn(new ArrayList<>());
        when(voteRepository.save(any())).thenReturn(VoteScenarioFactory.VOTE);
        assertNotNull(service.insertVote(VoteScenarioFactory.VOTE_REQUEST));
        verify(voteRepository,times(1)).save(any());
    }
    
}
