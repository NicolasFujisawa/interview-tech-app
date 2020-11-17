package br.com.interview.technicalapp.content.controller.v1.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.com.interview.technicalapp.content.model.Content;
import br.com.interview.technicalapp.question.controller.v1.dto.QuestionResponse;

import br.com.interview.technicalapp.question.model.Question;
import br.com.interview.technicalapp.user.controller.v1.dto.UserResponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContentResponse {

    private String title;

    private List<QuestionResponse> questions;

    private UUID owner;

    public static ContentResponse render(Content content) {
        var contentResponse = new ContentResponse();
        contentResponse.setTitle(content.getTitle());
        contentResponse.setQuestions(QuestionResponse.renderMany(content.getQuestions()));
        contentResponse.setOwner(content.getOwner().getId());

        return contentResponse;
    }

    public static List<ContentResponse> renderMany(List<Content> contents) {
        if(contents == null) {
            return new ArrayList<>();
        }
        List<ContentResponse> contentResponses = new ArrayList<>();
        for (Content content : contents) {
            contentResponses.add(render(content));
        }
        return contentResponses;
    }
}
