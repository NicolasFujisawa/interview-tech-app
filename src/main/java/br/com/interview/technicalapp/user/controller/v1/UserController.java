package br.com.interview.technicalapp.user.controller.v1;

import br.com.interview.technicalapp.question.controller.v1.dto.QuestionRequest;
import br.com.interview.technicalapp.question.service.QuestionService;
import br.com.interview.technicalapp.user.controller.v1.dto.UserRequest;
import br.com.interview.technicalapp.user.controller.v1.dto.UserResponse;
import br.com.interview.technicalapp.user.service.UserService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> list() {
        var users = userService.findAll();
        return ResponseEntity
                .ok(users.stream()
                        .map(UserResponse::render)
                        .collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest request) {
        var user = UserRequest.render(request);
        var userResponse = UserResponse.render(userService.create(user));

        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> show(@PathVariable("userId") String userId) {
        UUID id;
        try {
            id = UUID.fromString(userId);
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException(ex);
        }
        var user = userService.findById(id);

        return user.map(value -> ResponseEntity.ok(UserResponse.render(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponse> update(@PathVariable("userId") String userId,
                                               @RequestBody UserRequest userRequest) {
        UUID id;
        try {
            id = UUID.fromString(userId);
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException(ex);
        }
        var user = userService.findById(id);

        if(user.isPresent()) {
            var userSave = user.get();
            userSave.setUsername(userRequest.getNome());
            userSave.setPassword(userRequest.getSenha());
            return ResponseEntity.ok(UserResponse.render(userService.create(userSave)));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{userId}/questions")
    public ResponseEntity<UserResponse> createQuestion(@PathVariable("userId") UUID userId,
                                                       @RequestBody QuestionRequest questionRequest) {
        var user = this.userService.findById(userId);
        if (user.isPresent()) {
            var userPresent = user.get();
            var question = QuestionRequest.render(questionRequest);
            userPresent.getQuestions().add(question);
            this.questionService.save(question);
            this.userService.create(userPresent);
            return ResponseEntity.ok(UserResponse.render(userPresent));
        }
        return ResponseEntity.notFound().build();
    }
}
