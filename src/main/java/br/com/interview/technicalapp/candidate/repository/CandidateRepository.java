package br.com.interview.technicalapp.candidate.repository;

import br.com.interview.technicalapp.candidate.model.Candidate;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, UUID> {
}
