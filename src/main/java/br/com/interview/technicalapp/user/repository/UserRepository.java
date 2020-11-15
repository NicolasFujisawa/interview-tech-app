package br.com.interview.technicalapp.user.repository;

import br.com.interview.technicalapp.user.model.User;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UUID> {
}
