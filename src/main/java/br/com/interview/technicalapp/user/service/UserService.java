package br.com.interview.technicalapp.user.service;

import br.com.interview.technicalapp.user.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    Optional<User> findById(UUID userId);

    List<User> findAll();
}
