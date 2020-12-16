package br.com.interview.technicalapp.user.controller.v1.dto;

import br.com.interview.technicalapp.user.model.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

    @NotNull(message = "Username não pode ser nulo")
    @Size(min = 4, max = 32, message = "Username length not permitted")
    private String username;

    @NotNull(message = "Password could not be null")
    @Size(min = 4, max = 127, message = "Password length not permitted")
    private String password;

    @NotNull(message = "Password could not be null")
    @Size(min = 4, max = 320, message = "Email length not permitted")
    @Email(message = "Email format incorrect")
    private String email;

    public static User render(UserRequest u) {
        var user = new User();
        user.setUsername(u.getUsername());
        return user;
    }
}
