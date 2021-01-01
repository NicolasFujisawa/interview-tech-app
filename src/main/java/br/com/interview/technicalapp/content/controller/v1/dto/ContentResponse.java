package br.com.interview.technicalapp.content.controller.v1.dto;

import br.com.interview.technicalapp.content.controller.v1.ContentController;
import br.com.interview.technicalapp.content.model.Content;
import br.com.interview.technicalapp.question.controller.v1.dto.QuestionIdResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

@Getter
@Setter
public class ContentResponse extends RepresentationModel<ContentResponse> {

    private UUID id;

    private UUID owner;

    private String title;

    private List<QuestionIdResponse> questions;

    public static ContentResponse render(Content content) {
        var contentResponse = new ContentResponse();
        contentResponse.setId(content.getId());
        contentResponse.setOwner(content.getOwner().getId());
        contentResponse.setTitle(content.getTitle());
        content.getQuestions()
                .forEach(k -> contentResponse.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ContentController.class)
                        .show(k.getId())).withRel("questions")));
        contentResponse.setQuestions(QuestionIdResponse.renderMany(content.getQuestions()));

        return contentResponse;
    }

    public static List<ContentResponse> renderMany(Iterable<Content> contents) {
        if (contents == null) {
            return new ArrayList<>();
        }
        List<ContentResponse> contentResponses = new ArrayList<>();
        for (Content content : contents) {
            contentResponses.add(render(content));
        }
        return contentResponses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContentResponse)) return false;
        if (!super.equals(o)) return false;
        ContentResponse that = (ContentResponse) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(owner, that.owner) &&
                Objects.equals(title, that.title) &&
                Objects.equals(questions, that.questions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, owner, title, questions);
    }
}
