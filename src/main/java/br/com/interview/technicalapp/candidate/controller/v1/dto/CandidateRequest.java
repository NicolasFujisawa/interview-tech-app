package br.com.interview.technicalapp.candidate.controller.v1.dto;

import br.com.interview.technicalapp.candidate.model.Candidate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CandidateRequest {

    private String username;

    public static Candidate render(CandidateRequest candidateRequest) {
        var candidate = new Candidate();
        candidate.setUsername(candidateRequest.getUsername());

        return candidate;
    }
}
