package br.com.sicredi.voting.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.com.sicredi.voting.domain.Session;
import br.com.sicredi.voting.domain.dto.session.request.SessionRequest;
import br.com.sicredi.voting.domain.dto.session.response.SessionResponse;
import br.com.sicredi.voting.domain.enums.Status;
import br.com.sicredi.voting.repository.schedule.ScheduleRepository;
import br.com.sicredi.voting.repository.session.SessionRepository;
import br.com.sicredi.voting.validation.Message;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Validated
@AllArgsConstructor
@Slf4j
public class SessionService {
	
	private SessionRepository sessionRepository;
	
	private ScheduleRepository scheduleRepository;
	
	public SessionResponse insertSession(@Valid SessionRequest request) {
		var schedules = scheduleRepository.findAllById(request.getScheduleId());
	
		if (schedules.isEmpty()) {
			throw Message.NOT_FOUND_SCHEDULE.asBusinessException();
		}
	
		var session = Session.of(request, schedules);
		var sessionResponse = sessionRepository.save(session).toDto();
		log.info("method = insertSession sessionId = {}", sessionResponse.getSessionId());
		return sessionResponse;
	}

	public SessionResponse insertScheduleInSession(Long sessionId, Long scheduleId) {
		var schedule = scheduleRepository.findById(scheduleId)
				.orElseThrow(Message.NOT_FOUND_SCHEDULE::asBusinessException);
		var session = sessionRepository.findById(sessionId).orElseThrow(Message.NOT_FOUND_SESSION::asBusinessException);

		if (!session.getSchedules().contains(schedule)) {
			session.getSchedules().add(schedule);
		} else {
			throw Message.BAD_REQUEST_SCHEDULE.asBusinessException();
		}
		
		var sessionUpdate = sessionRepository.save(session).toDto();
		log.info("method = insertSession sessionId = {}", sessionUpdate.getSessionId());
		return sessionUpdate;
	}

	public List<SessionResponse> listAllOpenSessions() {
		log.info("method = listAllOpenSessions");		
		return sessionRepository.findByStatus(Status.OPEN).stream()
		.map(Session::toDto)
				.collect(Collectors.toList());

	}

}
