package br.com.interview.technicalapp.question.controller.v1.dto;

import br.com.interview.technicalapp.question.model.Question;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionRequest {

    @NotNull(message = "Titulo não pode ser nulo")
    @Size(min = 1, max = 126, message = "Titulo maior do que o permitido (126 characteres)")
    private String title;

    private String description;

    public static Question render(QuestionRequest questionRequest) {
        var question = new Question();
        question.setTitle(questionRequest.getTitle());
        question.setDescription(questionRequest.getDescription());
        return question;
    }
}
