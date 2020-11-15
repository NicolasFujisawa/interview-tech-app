package br.com.interview.technicalapp.user.repository;

import java.util.UUID;

import br.com.interview.technicalapp.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UUID> {
}
