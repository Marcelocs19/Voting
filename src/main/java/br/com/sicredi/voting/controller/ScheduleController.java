package br.com.sicredi.voting.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sicredi.voting.domain.dto.schedule.request.ScheduleRequest;
import br.com.sicredi.voting.domain.dto.schedule.response.ScheduleResponse;
import br.com.sicredi.voting.service.schedule.ScheduleService;
import lombok.AllArgsConstructor;



@RestController
@RequestMapping("/schedules")
@AllArgsConstructor
public class ScheduleController {
    
    private ScheduleService service;

    @PostMapping
    public ResponseEntity<ScheduleResponse> insertSchedule(@RequestBody ScheduleRequest request) {        
        return ResponseEntity.status(HttpStatus.CREATED).body(service.insertSchedule(request));
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponse>> listAllSchedules() {
        return ResponseEntity.status(HttpStatus.OK).body(service.listAllSchedules());
    }
    
    

}
