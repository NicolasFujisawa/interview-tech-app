package br.com.interview.technicalapp.content.controller.v1.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.com.interview.technicalapp.question.model.Question;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionResponse {

    private UUID id;

    public static QuestionResponse render(Question question) {
        var response = new QuestionResponse();
        response.setId(question.getId());
        return response;
    }

    public static List<QuestionResponse> renderMany(List<Question> questions) {
        List<QuestionResponse> responses = new ArrayList<>();
        for (Question question : questions) {
            responses.add(render(question));
        }
        return responses;
    }
}
