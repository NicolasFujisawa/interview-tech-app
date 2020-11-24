package br.com.interview.technicalapp.recruiter.controller.v1.dto;

import br.com.interview.technicalapp.recruiter.model.Recruiter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecruiterRequest {

    private String username;

    public static Recruiter render(RecruiterRequest recruiterRequest) {
        var recruiter = new Recruiter();
        recruiter.setUsername(recruiterRequest.getUsername());
        return recruiter;
    }
}
