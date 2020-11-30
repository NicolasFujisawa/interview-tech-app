package br.com.interview.technicalapp.recruiter.controller.v1;

import br.com.interview.technicalapp.business.CustomValid;
import br.com.interview.technicalapp.content.controller.v1.dto.ContentRequest;
import br.com.interview.technicalapp.content.controller.v1.dto.ContentResponse;
import br.com.interview.technicalapp.content.service.ContentService;
import br.com.interview.technicalapp.question.controller.v1.dto.QuestionRequest;
import br.com.interview.technicalapp.question.controller.v1.dto.QuestionResponse;
import br.com.interview.technicalapp.question.service.QuestionService;
import br.com.interview.technicalapp.recruiter.controller.v1.dto.RecruiterRequest;
import br.com.interview.technicalapp.recruiter.controller.v1.dto.RecruiterResponse;
import br.com.interview.technicalapp.recruiter.service.RecruiterService;

import javax.validation.Valid;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/v1/recruiters")
@Validated
public class RecruiterController {

    @Autowired
    private RecruiterService recruiterService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private ContentService contentService;

    @GetMapping
    public ResponseEntity<List<RecruiterResponse>> list() {
        var recruiters = this.recruiterService.findAll();

        return ResponseEntity.ok(recruiters
                .stream()
                .map(RecruiterResponse::render)
                .collect(Collectors.toList()));
    }

    @PostMapping("/{recruiterId}/questions")
    public ResponseEntity<QuestionResponse> createQuestion(@PathVariable("recruiterId") UUID recruiterId,
                                                           @RequestBody @Valid QuestionRequest questionRequest) {
        var recruiter = this.recruiterService.findById(recruiterId);
        if (recruiter.isPresent()) {
            var recruiterPresent = recruiter.get();
            var question = QuestionRequest.render(questionRequest);
            recruiterPresent.getQuestions().add(question);
            question.getRecruiters().add(recruiterPresent);
            this.questionService.save(question);
            return ResponseEntity.status(HttpStatus.CREATED).body(QuestionResponse.render(question));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{recruiterId}/contents")
    @CustomValid
    public ResponseEntity<ContentResponse> createContent(@PathVariable("recruiterId") UUID recruiterId,
                                                         @RequestBody @Valid ContentRequest contentRequest) {
        var recruiter = this.recruiterService.findById(recruiterId);
        if (recruiter.isPresent()) {
            var recruiterPresent = recruiter.get();
            var content = ContentRequest.render(contentRequest);
            content.setOwner(recruiterPresent);
            var saved = this.contentService.save(content);

            return ResponseEntity.status(HttpStatus.CREATED).body(ContentResponse.render(saved));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<RecruiterResponse> create(@RequestBody @Valid RecruiterRequest request) {
        var recruiter = RecruiterRequest.render(request);
        try {
            var recruiterResponse = RecruiterResponse.render(recruiterService.save(recruiter));
            return ResponseEntity.status(HttpStatus.CREATED).body(recruiterResponse);
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "username não pode ser vazio");
        }
    }

    @GetMapping("/{recruiterId}")
    public ResponseEntity<RecruiterResponse> show(@PathVariable("recruiterId") UUID recruiterId) {
        var recruiter = recruiterService.findById(recruiterId);

        return recruiter.map(value -> ResponseEntity.ok(RecruiterResponse.render(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{recruiterId}")
    public ResponseEntity<Void> update(@PathVariable UUID recruiterId,
                                       @RequestBody @Valid RecruiterRequest recruiterRequest) {
        var recruiterOptional = this.recruiterService.findById(recruiterId);

        if (recruiterOptional.isPresent()) {
            var recruiter = recruiterOptional.get();
            recruiter.setUsername(recruiterRequest.getUsername());
            this.recruiterService.save(recruiter);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{recruiterId}")
    public ResponseEntity<Void> delete(@PathVariable UUID recruiterId) {
        this.recruiterService.deleteById(recruiterId);

        return ResponseEntity.ok().build();
    }
}
