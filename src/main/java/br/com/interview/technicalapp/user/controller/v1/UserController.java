package br.com.interview.technicalapp.user.controller.v1;

import br.com.interview.technicalapp.config.JwtTokenUtil;
import br.com.interview.technicalapp.recruiter.model.RecruiterDetails;
import br.com.interview.technicalapp.user.controller.v1.dto.UserRequest;
import br.com.interview.technicalapp.user.controller.v1.dto.UserResponse;
import br.com.interview.technicalapp.user.model.User;
import br.com.interview.technicalapp.user.service.UserService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import liquibase.pro.packaged.U;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

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

    @PostMapping("/signin")
    public ResponseEntity<UserResponse> signin(@RequestBody UserRequest userRequest) {
        authenticate(userRequest.getUsername(), userRequest.getPassword());
        
        final UserDetails userDetails = userDetailsService.loadUserByUsername(userRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        User user = new User();

        if (userDetails instanceof RecruiterDetails) {
            user = ((RecruiterDetails) userDetails).getRecruiter();
        }
        var userResponse = UserResponse.render(user);
        userResponse.setToken(token);

        return ResponseEntity.ok(userResponse);
    }

    private void authenticate(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException | LockedException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User disabled or locked");
        } catch (BadCredentialsException e ) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Incorrect credentials");
        }
    }
}
