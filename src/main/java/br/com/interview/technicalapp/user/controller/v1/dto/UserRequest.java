package br.com.interview.technicalapp.user.controller.v1.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.interview.technicalapp.user.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

    @NotNull(message = "Username não pode ser nulo")
    @Size(min = 4, max = 25, message = "Tamanho do username não permitido")
    private String username;

    public static User render(UserRequest u) {
        var user = new User();
        user.setUsername(u.getUsername());
        return user;
    }
}
