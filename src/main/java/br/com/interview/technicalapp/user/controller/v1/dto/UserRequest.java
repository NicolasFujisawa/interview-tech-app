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
        user.setName(u.getNome());
        user.setLastname(u.getSenha());
        return user;
    }
}
