package br.com.sicredi.voting.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sicredi.voting.domain.dto.vote.request.VoteRequest;
import br.com.sicredi.voting.domain.dto.vote.response.VoteResponse;
import br.com.sicredi.voting.service.VoteService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/votes")
@AllArgsConstructor
public class VoteController {

    private VoteService service;

    @GetMapping
    public ResponseEntity<VoteResponse> insertVote(@RequestBody VoteRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(service.insertVote(request));
    }
    
}
