package br.com.interview.technicalapp.question.controller.v1.dto;

import br.com.interview.technicalapp.question.model.Question;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionRequest {

    private String title;

    private String description;

    public static Question render(QuestionRequest questionRequest) {
        var question = new Question();
        question.setTitle(questionRequest.getTitle());
        question.setDescription(questionRequest.getDescription());
        return question;
    }
}
