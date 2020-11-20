package br.com.interview.technicalapp.question.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.com.interview.technicalapp.question.model.Question;

import br.com.interview.technicalapp.question.repository.QuestionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Optional<Question> findById(UUID questionId) {
        return this.questionRepository.findById(questionId);
    }

    @Override
    public List<Question> findAll() {
        return this.questionRepository.findAll();
    }

    @Override
    public Question save(Question question) {
        return this.questionRepository.save(question);
    }

    @Override
    public void deleteById(UUID questionId) {
        this.questionRepository.deleteById(questionId);
    }

    @Override
    public boolean existsById(UUID questionId) {
        return this.questionRepository.existsById(questionId);
    }
}
