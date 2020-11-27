package br.com.interview.technicalapp.candidate.controller.v1.dto;

import br.com.interview.technicalapp.candidate.model.Candidate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CandidateRequest {

    @NotNull(message = "Username não pode estar vazio")
    @Size(min = 4, max = 25, message = "Tamanho do username não permitido")
    private String username;

    public static Candidate render(CandidateRequest candidateRequest) {
        var candidate = new Candidate();
        candidate.setUsername(candidateRequest.getUsername());

        return candidate;
    }
}
