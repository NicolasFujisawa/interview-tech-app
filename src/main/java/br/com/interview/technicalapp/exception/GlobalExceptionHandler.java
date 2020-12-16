package br.com.interview.technicalapp.exception;

import br.com.interview.technicalapp.recruiter.repository.exception.RecruiterNotFoundException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({IllegalArgumentException.class, TransactionSystemException.class})
    protected ResponseEntity<ErrorResponse> handleBadRequest(Exception ex) {
        log.info("From handleBadRequest", ex);
        return ResponseEntity.badRequest().body(
                ErrorResponse.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .message(ex.getMessage())
                        .build()
        );
    }

    @ExceptionHandler(ResponseStatusException.class)
    protected ResponseEntity<ErrorResponse> handleResponseStatusException(ResponseStatusException exception) {
        log.info("From handleResponseStatusException:", exception);
        return ResponseEntity.status(exception.getStatus()).body(
                ErrorResponse.builder()
                        .status(exception.getStatus())
                        .message(exception.getReason())
                        .build()
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex) {
        log.info("From handleConstraintViolationException");
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();

        Set<String> messages = new HashSet<>(constraintViolations.size());
        messages.addAll(constraintViolations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList()));

        return ResponseEntity.badRequest().body(
                ErrorResponse.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .message(String.join("\n", messages))
                        .build()
        );
    }

    @ExceptionHandler(RecruiterNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleRecruiterNotFoundException(RecruiterNotFoundException ex) {
        log.info("From handleRecruiterNotFoundException");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ErrorResponse.builder()
                        .status(HttpStatus.NOT_FOUND)
                        .message(ex.getMessage())
                        .build()
        );
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception ex) {
        log.info("From handleException", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ErrorResponse.builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .message(ex.getMessage())
                        .build()
        );
    }
}
