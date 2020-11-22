package br.com.interview.technicalapp.recruiter.controller.v1;

import java.util.UUID;

import br.com.interview.technicalapp.content.controller.v1.dto.ContentRequest;
import br.com.interview.technicalapp.content.controller.v1.dto.ContentResponse;
import br.com.interview.technicalapp.content.service.ContentService;
import br.com.interview.technicalapp.question.controller.v1.dto.QuestionRequest;
import br.com.interview.technicalapp.question.controller.v1.dto.QuestionResponse;
import br.com.interview.technicalapp.question.service.QuestionService;
import br.com.interview.technicalapp.recruiter.controller.v1.dto.RecruiterResponse;
import br.com.interview.technicalapp.recruiter.model.Recruiter;
import br.com.interview.technicalapp.recruiter.service.RecruiterService;

import br.com.interview.technicalapp.user.controller.v1.dto.UserRequest;
import br.com.interview.technicalapp.user.controller.v1.dto.UserResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/recruiters")
public class RecruiterController {

    @Autowired
    private RecruiterService recruiterService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private ContentService contentService;

    @PostMapping("/{recruiterId}/questions")
    public ResponseEntity<QuestionResponse> createQuestion(@PathVariable("recruiterId") UUID recruiterId,
                                                           @RequestBody QuestionRequest questionRequest) {
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
    public ResponseEntity<ContentResponse> createContent(@PathVariable("recruiterId") UUID recruiterId,
                                                         @RequestBody ContentRequest contentRequest) {
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
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest request) {
        Recruiter recruiter = (Recruiter) UserRequest.render(request);
        var userResponse = UserResponse.render(recruiterService.save(recruiter));

        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @GetMapping("/{recruiterId}")
    public ResponseEntity<RecruiterResponse> show(@PathVariable("recruiterId") UUID recruiterId) {
        var recruiter = recruiterService.findById(recruiterId);

        return recruiter.map(value -> ResponseEntity.ok(RecruiterResponse.render(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
