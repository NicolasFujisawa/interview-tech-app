package br.com.interview.technicalapp.question.controller.v1.dto;

import br.com.interview.technicalapp.question.model.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionIdResponse {

    private UUID id;

    public static QuestionIdResponse render(Question question) {
        var response = new QuestionIdResponse();
        response.setId(question.getId());
        return response;
    }

    public static List<QuestionIdResponse> renderMany(List<Question> questions) {
        List<QuestionIdResponse> responses = new ArrayList<>();
        for (Question question : questions) {
            responses.add(render(question));
        }
        return responses;
    }
}
