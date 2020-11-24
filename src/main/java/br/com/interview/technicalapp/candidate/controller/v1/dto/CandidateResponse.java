package br.com.interview.technicalapp.candidate.controller.v1.dto;

import java.util.List;

import br.com.interview.technicalapp.candidate.model.Candidate;

import br.com.interview.technicalapp.content.controller.v1.dto.ContentResponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CandidateResponse {

    private String username;

    private List<ContentResponse> availableContents;

    public static CandidateResponse render(Candidate candidate) {
        var candidateResponse = new CandidateResponse();
        candidateResponse.setUsername(candidate.getUsername());
        candidateResponse.setAvailableContents(ContentResponse.renderMany(candidate.getAvailableContents()));

        return candidateResponse;
    }
}
