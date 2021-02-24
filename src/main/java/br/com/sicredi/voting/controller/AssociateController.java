package br.com.sicredi.voting.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sicredi.voting.annotation.InsertAssociatePostCodeStandard;
import br.com.sicredi.voting.domain.dto.associate.request.AssociateRequest;
import br.com.sicredi.voting.domain.dto.associate.response.AssociateResponse;
import br.com.sicredi.voting.service.AssociateService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/associates")
@Tag(name = "Associates")
@AllArgsConstructor
public class AssociateController {
    
    private AssociateService service;

    @PostMapping
    @InsertAssociatePostCodeStandard
    public ResponseEntity<AssociateResponse> insertAssociate(@RequestBody AssociateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.insertAssociate(request));
    }

}
