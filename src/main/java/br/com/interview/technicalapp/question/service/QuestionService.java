package br.com.interview.technicalapp.question.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.com.interview.technicalapp.question.model.Question;

public interface QuestionService {

    Optional<Question> findById(UUID questionId);

    List<Question> findAll();

    Question save(Question question);

    void deleteById(UUID questionId);

    boolean existsById(UUID questionId);
}
