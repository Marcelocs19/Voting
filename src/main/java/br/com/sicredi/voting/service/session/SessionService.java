package br.com.sicredi.voting.service.session;

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
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Validated
@AllArgsConstructor
@Slf4j
public class SessionService {

	private SessionRepository sessionRepository;

	private ScheduleRepository scheduleRepository;

	public List<SessionResponse> listAllOpenSessions() {
		log.info("method = listAllOpenSessions");
		return sessionRepository.findByStatus(Status.OPEN).stream()
				.map(session -> new SessionResponse(session.getSessionId(), session.getMeetingDate(),
						session.getSchedules(), session.getDuration(), session.getStatus()))
				.collect(Collectors.toList());

	}
	
	public SessionResponse insertSession(@Valid SessionRequest request) {
		var schedules = scheduleRepository.findAllById(request.getScheduleId());
		var session = Session.of(request, schedules);
		var sessionResponse = sessionRepository.save(session).toDto();
		log.info("method = insertSession sessionId = {}", sessionResponse.getSessionId());
		return sessionResponse;
	}

}
