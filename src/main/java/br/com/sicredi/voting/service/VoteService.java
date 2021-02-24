package br.com.sicredi.voting.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;

import br.com.sicredi.voting.domain.Vote;
import br.com.sicredi.voting.domain.dto.vote.request.VoteRequest;
import br.com.sicredi.voting.domain.dto.vote.response.VoteResponse;
import br.com.sicredi.voting.repository.AssociateRepository;
import br.com.sicredi.voting.repository.ScheduleRepository;
import br.com.sicredi.voting.repository.SessionRepository;
import br.com.sicredi.voting.repository.VoteRepository;
import br.com.sicredi.voting.validation.Message;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Validated
@Slf4j
public class VoteService {

    private VoteRepository voteRepository;

    private AssociateRepository associateRepository;

    private ScheduleRepository scheduleRepository;

    private SessionRepository sessionRepository;

    public VoteResponse insertVote(@Valid VoteRequest request) {
        RestTemplate restTemplate = new RestTemplate();

        var session = sessionRepository.findById(request.getSessionId())
                .orElseThrow(Message.NOT_FOUND_SESSION::asBusinessException);

        if (!session.checkSchedule()) {
            sessionRepository.save(session);
            throw Message.BAD_REQUEST_SCHEDULE_CLOSED.asBusinessException();
        }

        var schedule = scheduleRepository.findById(request.getScheduleId())
                .orElseThrow(Message.NOT_FOUND_SCHEDULE::asBusinessException);

        var associate = associateRepository.findByCpf(request.getCpf())
                .orElseThrow(Message.NOT_FOUND_ASSOCIATE::asBusinessException);

        ResponseEntity<String> response;
        try {
            response = restTemplate.getForEntity("https://user-info.herokuapp.com/users/" + request.getCpf(),
                    String.class);
        } catch (Exception e) {
            throw Message.NOT_FOUND_VOTE.asBusinessException();
        }

        String body = response.getBody();
        if (body.contains("ABLE_TO_VOTE")) {
            session.getSchedules().stream().filter(sh -> sh.getScheduleId().equals(request.getScheduleId())).findFirst()
                    .orElseThrow(Message.NOT_FOUND_SCHEDULE_AT_SESSION::asBusinessException);

            List<Vote> list = voteRepository.findByAssociateAndSchedule(associate, schedule);
            if (list.isEmpty()) {
                var vote = voteRepository.save(Vote.of(request, associate, schedule));
                log.info("method = insertVote voteId = {}", vote.getVoteId());
            } else {
                throw Message.BAD_REQUEST_VOTE.asBusinessException();
            }
        } else {
            throw Message.BAD_REQUEST_VOTE.asBusinessException();
        }

        return new VoteResponse("Voto salvo com sucesso!");
    }

}
