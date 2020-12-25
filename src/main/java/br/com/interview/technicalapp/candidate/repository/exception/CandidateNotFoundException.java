package br.com.interview.technicalapp.candidate.repository.exception;

public class CandidateNotFoundException extends RuntimeException {
    public CandidateNotFoundException() {
        super();
    }

    public CandidateNotFoundException(Object message) {
        super(messagePattern(message));
    }

    public CandidateNotFoundException(Object message, Throwable cause) {
        super(messagePattern(message), cause);
    }

    private static String messagePattern(Object message) {
        return "Candidate " + message.toString() + " not found!";
    }
}
