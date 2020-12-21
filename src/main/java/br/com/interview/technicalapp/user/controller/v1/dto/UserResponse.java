package br.com.interview.technicalapp.user.controller.v1.dto;

import br.com.interview.technicalapp.user.model.User;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

    private UUID id;

    private String username;

    private String token;

    private Boolean isEnabled;

    public static UserResponse render(User u) {
        var user = new UserResponse();
        user.setId(u.getId());
        user.setUsername(u.getUsername());
        user.setIsEnabled(u.isEnabled());

        return user;
    }
}
