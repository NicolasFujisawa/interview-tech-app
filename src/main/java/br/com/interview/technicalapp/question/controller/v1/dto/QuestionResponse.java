package br.com.interview.technicalapp.question.controller.v1.dto;

import br.com.interview.technicalapp.question.model.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionResponse {

    private UUID id;

    private String title;

    private String description;

    public static QuestionResponse render(Question question) {
        QuestionResponse response = new QuestionResponse();
        response.setId(question.getId());
        response.setTitle(question.getTitle());
        response.setDescription(question.getDescription());
        return response;
    }

    public static List<QuestionResponse> renderMany(List<Question> questions) {
        if (questions == null) {
            return new ArrayList<>();
        }
        List<QuestionResponse> questionsResponse = new ArrayList<>();
        for (Question question : questions) {
            questionsResponse.add(render(question));
        }
        return questionsResponse;
    }
}
