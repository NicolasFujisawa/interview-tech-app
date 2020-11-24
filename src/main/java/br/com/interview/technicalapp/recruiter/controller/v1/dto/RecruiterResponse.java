package br.com.interview.technicalapp.recruiter.controller.v1.dto;

import br.com.interview.technicalapp.content.controller.v1.dto.ContentResponse;
import br.com.interview.technicalapp.question.controller.v1.dto.QuestionResponse;
import br.com.interview.technicalapp.recruiter.model.Recruiter;

import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecruiterResponse {

    private UUID id;

    private String username;

    private List<ContentResponse> contents;

    private List<QuestionResponse> questions;

    public static RecruiterResponse render(Recruiter recruiter) {
        var recruiterResponse = new RecruiterResponse();
        recruiterResponse.setId(recruiter.getId());
        recruiterResponse.setUsername(recruiter.getUsername());
        recruiterResponse.setContents(ContentResponse.renderMany(recruiter.getContents()));
        recruiterResponse.setQuestions(QuestionResponse.renderMany(recruiter.getQuestions()));
        return recruiterResponse;
    }
}
