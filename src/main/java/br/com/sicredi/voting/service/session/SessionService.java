package br.com.sicredi.voting.service.session;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import br.com.sicredi.voting.domain.Session;
import br.com.sicredi.voting.domain.dto.session.request.SessionRequest;
import br.com.sicredi.voting.domain.dto.session.response.SessionResponse;
import br.com.sicredi.voting.domain.enums.Status;
import br.com.sicredi.voting.repository.schedule.ScheduleRepository;
import br.com.sicredi.voting.repository.session.SessionRepository;
import lombok.AllArgsConstructor;

@Service
@Validated
@AllArgsConstructor
public class SessionService {

	private SessionRepository sessionRepository;

	private ScheduleRepository scheduleRepository;

	public List<SessionResponse> listAllOpenSessions() {
		return sessionRepository.findByStatus(Status.OPEN).stream()
				.map(session -> new SessionResponse(session.getSessionId(), session.getMeetingDate(),
						session.getSchedule(), session.getDuration(), session.getStatus()))
				.collect(Collectors.toList());

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public SessionResponse insertSession(@Valid SessionRequest request) {
		var schedules = scheduleRepository.findAllById(request.getScheduleId());
		var session = Session.of(request);
		session.addSchedule(schedules);
		Session save = sessionRepository.save(session);
		schedules.get(0).setSession(save);
		scheduleRepository.saveAll(schedules);
		return save.toDto();
	}

}
