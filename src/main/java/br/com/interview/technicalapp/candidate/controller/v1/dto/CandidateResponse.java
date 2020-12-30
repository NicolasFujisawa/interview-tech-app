package br.com.interview.technicalapp.candidate.controller.v1.dto;

import br.com.interview.technicalapp.candidate.model.Candidate;
import br.com.interview.technicalapp.content.controller.v1.dto.ContentResponse;

import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CandidateResponse {

    private UUID id;

    private String username;

    private List<ContentResponse> availableContents;

    public static CandidateResponse parse(Candidate candidate) {
        var candidateResponse = new CandidateResponse();
        candidateResponse.setId(candidate.getId());
        candidateResponse.setUsername(candidate.getUsername());
        candidateResponse.setAvailableContents(ContentResponse.renderMany(candidate.getAvailableContents()));

        return candidateResponse;
    }
}
