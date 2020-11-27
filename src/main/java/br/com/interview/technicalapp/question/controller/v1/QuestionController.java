package br.com.interview.technicalapp.question.controller.v1;

import javax.validation.Valid;

import br.com.interview.technicalapp.question.controller.v1.dto.QuestionRequest;
import br.com.interview.technicalapp.question.controller.v1.dto.QuestionResponse;
import br.com.interview.technicalapp.question.service.QuestionService;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/v1/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/{questionId}")
    public ResponseEntity<QuestionResponse> show(@PathVariable UUID questionId) {
        var question = this.questionService.findById(questionId);

        return question.map(q -> ResponseEntity.ok(QuestionResponse.render(q)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity<Void> delete(@PathVariable UUID questionId) {
        this.questionService.deleteById(questionId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{questionId}")
    public ResponseEntity<QuestionResponse> update(@PathVariable UUID questionId,
                                                   @RequestBody @Valid QuestionRequest questionRequest) {
        var question = this.questionService.findById(questionId);
        if (question.isPresent()) {
            var questionSave = question.get();
            questionSave.setTitle(questionRequest.getTitle());
            questionSave.setDescription(questionRequest.getDescription());
            try {
                this.questionService.save(questionSave);
            } catch (TransactionSystemException ex) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Campo não pode ser nulo", ex);
            }
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.notFound().build();
    }

}
