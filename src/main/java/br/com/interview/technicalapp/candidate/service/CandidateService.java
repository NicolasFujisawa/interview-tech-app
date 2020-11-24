package br.com.interview.technicalapp.candidate.service;

import br.com.interview.technicalapp.candidate.model.Candidate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CandidateService {

    List<Candidate> findAll();

    Candidate save(Candidate candidate);

    void deleteById(UUID candidateId);

    Optional<Candidate> findById(UUID candidateId);
}
