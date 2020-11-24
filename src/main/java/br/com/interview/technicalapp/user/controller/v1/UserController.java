package br.com.interview.technicalapp.user.controller.v1;

import br.com.interview.technicalapp.user.controller.v1.dto.UserResponse;
import br.com.interview.technicalapp.user.service.UserService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> list() {
        var users = userService.findAll();
        return ResponseEntity
                .ok(users.stream()
                        .map(UserResponse::render)
                        .collect(Collectors.toList()));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> show(@PathVariable("userId") UUID userId) {
        var user = userService.findById(userId);

        return user.map(value -> ResponseEntity.ok(UserResponse.render(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
