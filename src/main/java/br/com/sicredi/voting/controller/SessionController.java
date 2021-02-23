package br.com.sicredi.voting.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sicredi.voting.domain.dto.session.request.SessionRequest;
import br.com.sicredi.voting.domain.dto.session.response.SessionResponse;
import br.com.sicredi.voting.service.session.SessionService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/sessions")
@AllArgsConstructor
public class SessionController {
    
    private SessionService service;

    @PostMapping
    public ResponseEntity<SessionResponse> insertSession(@RequestBody SessionRequest request) {        
        return ResponseEntity.status(HttpStatus.CREATED).body(service.insertSession(request));
    }

    @GetMapping
    public ResponseEntity<List<SessionResponse>> listAllOpenSessions() {
        return ResponseEntity.status(HttpStatus.OK).body(service.listAllOpenSessions());
    }

}
