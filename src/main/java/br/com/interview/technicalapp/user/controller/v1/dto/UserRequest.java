package br.com.interview.technicalapp.user.controller.v1.dto;

import br.com.interview.technicalapp.user.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

    private String nome;

    private String senha;

    public static User render(UserRequest u) {
        var user = new User();
        user.setUsername(u.getNome());
        user.setPassword(u.getSenha());
        return user;
    }
}
