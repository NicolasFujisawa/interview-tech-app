package br.com.interview.technicalapp.candidate.controller.v1;

import br.com.interview.technicalapp.candidate.model.Candidate;
import br.com.interview.technicalapp.candidate.service.CandidateService;

import br.com.interview.technicalapp.user.controller.v1.dto.UserRequest;

import br.com.interview.technicalapp.user.controller.v1.dto.UserResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/candidate")
public class CandidateController {

    @Autowired
    CandidateService candidateService;

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest candidateRequest) {
        var candidate = (Candidate) UserRequest.render(candidateRequest);
        var candidateResponse= UserResponse.render(this.candidateService.save(candidate));

        return ResponseEntity.status(HttpStatus.CREATED).body(candidateResponse);
    }
}
