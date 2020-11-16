package br.com.interview.technicalapp.question.repository;

import java.util.UUID;

import br.com.interview.technicalapp.question.model.Question;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, UUID> {
}
