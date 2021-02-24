package br.com.sicredi.voting.service;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;

import br.com.sicredi.voting.domain.Vote;
import br.com.sicredi.voting.domain.dto.vote.request.VoteRequest;
import br.com.sicredi.voting.domain.dto.vote.response.VoteResponse;
import br.com.sicredi.voting.repository.associate.AssociateRepository;
import br.com.sicredi.voting.repository.schedule.ScheduleRepository;
import br.com.sicredi.voting.repository.session.SessionRepository;
import br.com.sicredi.voting.repository.vote.VoteRepository;
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
        ResponseEntity<String> response;
        try {
            response = restTemplate.getForEntity("https://user-info.herokuapp.com/users/" + request.getCpf(),
                    String.class);
        } catch (Exception e) {
            throw Message.NOT_FOUND_VOTE.asBusinessException();
        }

        String body = response.getBody();
        if (body.contains("ABLE_TO_VOTE")) {
            var associate = associateRepository.findByCpf(request.getCpf())
                    .orElseThrow(Message.NOT_FOUND_ASSOCIATE::asBusinessException);

            var schedule = scheduleRepository.findById(request.getScheduleId())
                    .orElseThrow(Message.NOT_FOUND_SCHEDULE::asBusinessException);

            var sessions = sessionRepository.findById(request.getSessionId())
                    .orElseThrow(Message.NOT_FOUND_SESSION::asBusinessException);
                    
            sessions.getSchedules().stream().filter(sh -> sh.getScheduleId().equals(request.getScheduleId()))
                    .findFirst().orElseThrow(Message.NOT_FOUND_SCHEDULE_AT_SESSION::asBusinessException);

            var vote = voteRepository.save(Vote.of(request, associate, schedule));    
            log.info("method = insertVote voteId = {}", vote.getVoteId());        
        } else {
            throw Message.BAD_REQUEST_VOTE.asBusinessException();
        }

        return new VoteResponse("Voto salvo com sucesso!");
    }

}
