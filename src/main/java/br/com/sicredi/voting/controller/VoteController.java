package br.com.sicredi.voting.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sicredi.voting.domain.dto.vote.request.VoteRequest;
import br.com.sicredi.voting.domain.dto.vote.response.VoteResponse;
import br.com.sicredi.voting.service.VoteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/votes")
@Tag(name = "Votes")
@AllArgsConstructor
public class VoteController {

    private VoteService service;

    @PostMapping
    public ResponseEntity<VoteResponse> insertVote(@RequestBody VoteRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(service.insertVote(request));
    }
    
}
