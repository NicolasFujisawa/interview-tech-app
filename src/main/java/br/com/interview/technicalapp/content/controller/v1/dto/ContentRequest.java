package br.com.interview.technicalapp.content.controller.v1.dto;

import br.com.interview.technicalapp.content.model.Content;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContentRequest {

    private String title;

    public static Content render(ContentRequest contentRequest) {
        var content = new Content();
        content.setTitle(contentRequest.getTitle());

        return content;
    }
}
