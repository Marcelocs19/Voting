package br.com.sicredi.voting.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.com.sicredi.voting.domain.Session;
import br.com.sicredi.voting.domain.dto.session.request.SessionRequest;
import br.com.sicredi.voting.domain.dto.session.response.SessionResponse;
import br.com.sicredi.voting.repository.ScheduleRepository;
import br.com.sicredi.voting.repository.SessionRepository;
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

	public List<SessionResponse> listAllSessions() {
		log.info("method = listSessions");
		List<Session> sessions = sessionRepository.findAll();
		List<Session> checkSessions = Session.checkListSessions(sessions);
		sessionRepository.saveAll(checkSessions);
		return checkSessions.stream().map(Session::toDto).collect(Collectors.toList());
	}

	public SessionResponse getSession(Long sessionId) {
		return sessionRepository.findById(sessionId).orElseThrow(Message.NOT_FOUND_SESSION::asBusinessException).toDto();
	}

}
