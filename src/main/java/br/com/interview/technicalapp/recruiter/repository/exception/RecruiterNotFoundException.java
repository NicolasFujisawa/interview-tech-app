package br.com.interview.technicalapp.recruiter.repository.exception;

public class RecruiterNotFoundException extends RuntimeException {

    public RecruiterNotFoundException() {
        super();
    }

    public RecruiterNotFoundException(Object message) {
        super(messagePattern(message));
    }

    public RecruiterNotFoundException(Object message, Throwable cause) {
        super(messagePattern(message), cause);
    }

    private static String messagePattern(Object message) {
        return "Recruiter " + message.toString() + " not found!";
    }
}
