package br.com.interview.technicalapp.recruiter.repository;

import java.util.UUID;

import br.com.interview.technicalapp.recruiter.model.Recruiter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruiterRepository extends JpaRepository<Recruiter, UUID> {

    Recruiter findByUsername(String username);
}
