package br.com.interview.technicalapp.content.controller.v1.dto;

import br.com.interview.technicalapp.content.model.Content;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContentResponse {

    private UUID id;

    private String title;

    private List<QuestionResponse> questions;

    private UUID owner;

    public static ContentResponse render(Content content) {
        var contentResponse = new ContentResponse();
        contentResponse.setId(content.getId());
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
