package br.com.sicredi.voting.service.schedule;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.com.sicredi.voting.domain.Schedule;
import br.com.sicredi.voting.domain.dto.schedule.request.ScheduleRequest;
import br.com.sicredi.voting.domain.dto.schedule.response.ScheduleResponse;
import br.com.sicredi.voting.repository.schedule.ScheduleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Validated
@Slf4j
public class ScheduleService {

    private ScheduleRepository repository;

	public List<ScheduleResponse> listAllSchedules() {
        log.info("method = listAllSchedules");
        return repository.findAllSchedules();
	}

	public ScheduleResponse insertSchedule(@Valid ScheduleRequest request) {
        var schedule = repository.save(Schedule.of(request));
        log.info("method = insertSchedule scheduleId = {}", schedule.getScheduleId());
		return schedule.toDto();
	}

	
    
}
