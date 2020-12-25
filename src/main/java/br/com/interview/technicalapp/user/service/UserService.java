package br.com.interview.technicalapp.user.service;

import br.com.interview.technicalapp.user.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Inteface for CRUD operations on a service for {@link User} entity.
 */
public interface UserService {

    /**
     * Find user by the given id.
     *
     * @param userId must not be null
     * @return user with given id or {@link Optional#empty()} if not found
     */
    Optional<User> findById(UUID userId);

    /**
     * Find all users.
     *
     * @return all users
     */
    List<User> findAll();

    /**
     * Returns whether an user with the given id exists.
     *
     * @param username must not be {@literal null}.
     * @return {@literal true} if a candidate with the given username exists, {@literal false} otherwise.
     * @throws IllegalArgumentException if {@literal id} is {@literal null}.
     */
    boolean existsByUsername(String username);
}
