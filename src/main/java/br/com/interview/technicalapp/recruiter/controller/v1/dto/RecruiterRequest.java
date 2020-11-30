package br.com.interview.technicalapp.recruiter.controller.v1.dto;

import br.com.interview.technicalapp.recruiter.model.Recruiter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecruiterRequest {

    @NotNull(message = "Username não pode estar vazio")
    @Size(min = 4, max = 25, message = "Tamanho do username não permitido")
    private String username;

    public static Recruiter render(RecruiterRequest recruiterRequest) {
        var recruiter = new Recruiter();
        recruiter.setUsername(recruiterRequest.getUsername());
        return recruiter;
    }
}
