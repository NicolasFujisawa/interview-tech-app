package br.com.interview.technicalapp.user.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.com.interview.technicalapp.user.model.User;

public interface UserService {

    User create(User user);

    Optional<User> findById(UUID userId);

    List<User> findAll();
}
