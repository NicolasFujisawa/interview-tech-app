package br.com.interview.technicalapp.user.controller.v1.dto;

import java.util.List;

import br.com.interview.technicalapp.content.controller.v1.dto.ContentResponse;
import br.com.interview.technicalapp.question.controller.v1.dto.QuestionResponse;
import br.com.interview.technicalapp.question.model.Question;
import br.com.interview.technicalapp.user.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

    private String id;

    private String nome;

    private String senha;

    private List<QuestionResponse> questions;

    private List<ContentResponse> contents;

    public static UserResponse render(User u) {
        var user = new UserResponse();
        user.setId(u.getId().toString());
        user.setNome(u.getUsername());
        user.setSenha(u.getPassword());
        user.setQuestions(QuestionResponse.renderMany(u.getQuestions()));
        user.setContents(ContentResponse.renderMany(u.getContents()));

        return user;
    }
}
