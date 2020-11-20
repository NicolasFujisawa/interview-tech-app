package br.com.interview.technicalapp.content.controller.v1;

import br.com.interview.technicalapp.content.controller.v1.dto.ContentResponse;
import br.com.interview.technicalapp.content.controller.v1.dto.QuestionRequest;
import br.com.interview.technicalapp.content.service.ContentService;

import java.util.List;
import java.util.UUID;

import br.com.interview.technicalapp.question.service.QuestionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/contents")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/{contentId}")
    public ResponseEntity<ContentResponse> show(@PathVariable("contentId") UUID contentId) {
        var content = this.contentService.findById(contentId);

        return content.map(c -> ResponseEntity.ok(ContentResponse.render(c)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{contentId}/questions")
    public ResponseEntity<ContentResponse> updateQuestion(@PathVariable("contentId") UUID contentId,
                                                          @RequestBody QuestionRequest questionRequest) {
        var question = this.questionService.findById(questionRequest.getId());
        if (question.isPresent()) {
            var content = this.contentService.findById(contentId);
            return content.map(c -> {
                c.getQuestions().add(question.get());
                this.contentService.save(c);
                return ResponseEntity.ok(ContentResponse.render(c));
            }).orElseGet(() -> ResponseEntity.notFound().build());
        }
        return ResponseEntity.notFound().build();
    }
}
