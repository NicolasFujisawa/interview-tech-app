package br.com.interview.technicalapp.candidate.controller.v1.dto;

import br.com.interview.technicalapp.candidate.controller.v1.CandidateController;
import br.com.interview.technicalapp.candidate.model.Candidate;

import java.util.Objects;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

@Getter
@Setter
public class CandidateResponse extends RepresentationModel<CandidateResponse> {

    private UUID id;

    private String username;

    private String email;

    public static CandidateResponse parse(Candidate candidate) {
        var candidateResponse = new CandidateResponse();
        candidateResponse.setId(candidate.getId());
        candidateResponse.setUsername(candidate.getUsername());
        candidateResponse.setEmail(candidate.getEmail());
        candidateResponse.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CandidateController.class)
                .show(candidateResponse.getId())).withSelfRel());
        candidateResponse.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CandidateController.class)
                .listContents(candidateResponse.getId())).withRel("availableContents"));

        return candidateResponse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CandidateResponse)) return false;
        if (!super.equals(o)) return false;
        CandidateResponse that = (CandidateResponse) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(username, that.username) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, username, email);
    }
}
