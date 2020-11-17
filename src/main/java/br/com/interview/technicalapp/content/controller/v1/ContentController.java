package br.com.interview.technicalapp.content.controller.v1;

import java.util.List;
import java.util.UUID;

import br.com.interview.technicalapp.content.controller.v1.dto.ContentResponse;
import br.com.interview.technicalapp.content.service.ContentService;

import br.com.interview.technicalapp.question.controller.v1.dto.QuestionResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/contents")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @GetMapping("/{contentId}")
    public ResponseEntity<ContentResponse> show(@PathVariable("contentId") UUID contentId) {
        var content = this.contentService.findById(contentId);

        return content.map(c -> ResponseEntity.ok(ContentResponse.render(c)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
