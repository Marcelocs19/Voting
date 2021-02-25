package br.com.sicredi.voting.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.sicredi.voting.feature.ScheduleScenarioFactory;
import br.com.sicredi.voting.repository.ScheduleRepository;

@RunWith(MockitoJUnitRunner.class)
public class ScheduleServiceTest {
    
    @InjectMocks
    ScheduleService service;

    @Mock
    ScheduleRepository repository;

    @Test
    public void insertSchedule_WhenScheduleRequestIsValid_ExpectedOk() {        
        when(repository.save(any())).thenReturn(ScheduleScenarioFactory.SCHEDULE);
        assertNotNull(service.insertSchedule(ScheduleScenarioFactory.SCHEDULE_REQUEST));
        verify(repository,times(1)).save(any());
    }

    @Test
    public void listAllSchedules_WhenSchedulesRequestIsValid_ExpectedOk() {        
        assertNotNull(service.listAllSchedules());
        verify(repository,times(1)).findAll();
    }

}
