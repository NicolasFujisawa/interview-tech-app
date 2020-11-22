package br.com.interview.technicalapp.user.controller.v1.dto;

import br.com.interview.technicalapp.user.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

    private String id;

    private String nome;

    private String sobrenome;

    public static UserResponse render(User u) {
        var user = new UserResponse();
        user.setId(u.getId().toString());
        user.setNome(u.getName());
        user.setSobrenome(u.getLastname());

        return user;
    }
}
