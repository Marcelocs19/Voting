package br.com.sicredi.voting.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
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

import br.com.sicredi.voting.domain.Schedule;
import br.com.sicredi.voting.exception.BusinessException;
import br.com.sicredi.voting.feature.SessionScenarioFactory;
import br.com.sicredi.voting.repository.ScheduleRepository;
import br.com.sicredi.voting.repository.SessionRepository;

@RunWith(MockitoJUnitRunner.class)
public class SessionServiceTest {

    @InjectMocks
    SessionService service;

    @Mock
    SessionRepository sessionRepository;

    @Mock
	ScheduleRepository scheduleRepository;
    
    @Test
    public void getSession_WhenSessionRequestIsValid_ExpectedOk() {
        when(sessionRepository.findById(any())).thenReturn(Optional.of(SessionScenarioFactory.SESSION));
        assertNotNull(service.getSession(SessionScenarioFactory.SESSION_ID));
        verify(sessionRepository, times(1)).findById(any());
    }

    @Test(expected = BusinessException.class)
    public void getSession_WhenSessionRequestIsNotValid_ExpectedBadRequest() {
        assertNotNull(service.getSession(SessionScenarioFactory.SESSION_ID));
        verify(sessionRepository, times(1)).findById(any());
    }

    @Test
    public void listAllSessions_WhenSessionRequestIsValid_ExpectedOk() {
        when(sessionRepository.saveAll(any())).thenReturn(new ArrayList<>());
        assertNotNull(service.listAllSessions());
        verify(sessionRepository, times(1)).findAll();
        verify(sessionRepository, times(1)).saveAll(any());
    }

    @Test
    public void insertSession_WhenSessionRequestIsValid_ExpectedOk() {             
        when(scheduleRepository.findAllById(any())).thenReturn(SessionScenarioFactory.SCHEDULE_LIST);
        when(sessionRepository.save(any())).thenReturn(SessionScenarioFactory.SESSION_INSERT);
        assertNotNull(service.insertSession(SessionScenarioFactory.SESSION_REQUEST));
        verify(scheduleRepository, times(1)).findAllById(any());
        verify(sessionRepository, times(1)).save(any());
    }

    @Test(expected = BusinessException.class)
    public void insertSession_WhenSessionRequestIsNotValid_ExpectedNotFound() {
        when(scheduleRepository.findAllById(any())).thenReturn(new ArrayList<Schedule>());
        assertNotNull(service.insertSession(SessionScenarioFactory.SESSION_REQUEST));
        verify(scheduleRepository, times(1)).findAllById(any());
    }
}
