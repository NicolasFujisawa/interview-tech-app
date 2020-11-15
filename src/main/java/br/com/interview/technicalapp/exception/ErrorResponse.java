package br.com.interview.technicalapp.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Builder
@Getter
@Setter
public class ErrorResponse {
    private HttpStatus status;
    private String message;
    private String name;
}
