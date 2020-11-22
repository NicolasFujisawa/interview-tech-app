package br.com.interview.technicalapp.recruiter.service;

import java.util.Optional;
import java.util.UUID;

import br.com.interview.technicalapp.recruiter.model.Recruiter;

public interface RecruiterService {

    Recruiter save(Recruiter recruiter);

    void deleteById(UUID recruiterId);

    Optional<Recruiter> findById(UUID recruiterId);
}
