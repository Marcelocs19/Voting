package br.com.sicredi.voting.service;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;

import br.com.sicredi.voting.domain.dto.vote.request.VoteRequest;
import br.com.sicredi.voting.domain.dto.vote.response.VoteResponse;
import br.com.sicredi.voting.repository.vote.VoteRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Validated
public class VoteService {
    
    private VoteRepository voteRepository;

    public VoteResponse insertVote(@Valid VoteRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("https://user-info.herokuapp.com/users/" + request.getCpf(), String.class);
        String body = response.getBody();        
        if(body.contains("ABLE_TO_VOTE")) {
            
        }
        return null;
    }

}
