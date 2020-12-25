package br.com.interview.technicalapp.user.controller.v1.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


/**
 * DTO Response to verify if an user exists.
 */
@Getter
@Setter
@Builder
public class UserValidResponse {

    private Boolean valid;

    private List<String> messages;
}
