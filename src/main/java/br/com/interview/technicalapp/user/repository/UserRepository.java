package br.com.interview.technicalapp.user.repository;

import br.com.interview.technicalapp.user.model.User;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UUID> {

    <S extends User> Optional<S> findByUsername(String username);

    boolean existsByUsername(String username);
}
