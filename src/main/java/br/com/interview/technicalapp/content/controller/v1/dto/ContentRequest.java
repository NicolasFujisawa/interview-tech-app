package br.com.interview.technicalapp.content.controller.v1.dto;

import br.com.interview.technicalapp.content.model.Content;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContentRequest {

    @NotNull(message = "Titulo não pode ser nulo")
    @Size(min = 1, max = 82, message = "Tamanho do titulo maior do que o permitido (82 characteres)")
    private String title;

    public static Content render(ContentRequest contentRequest) {
        var content = new Content();
        content.setTitle(contentRequest.getTitle());

        return content;
    }
}
