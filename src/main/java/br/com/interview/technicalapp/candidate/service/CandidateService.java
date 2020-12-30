package br.com.interview.technicalapp.candidate.service;

import br.com.interview.technicalapp.candidate.model.Candidate;

import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Interface for CRUD operations for {@link Candidate} entity
 */
public interface CandidateService {

    /**
     * Find all Candidates.
     *
     * @return all candidates
     */
    List<Candidate> findAll();

    /**
     * Save candidate.
     *
     * @param candidate must not be null
     * @return saved candidate, will never be null
     * @throws IllegalArgumentException in case the given {@literal candidate} is {@literal null}.
     */
    Candidate save(Candidate candidate);

    /**
     * Delete candidate by the given id.
     *
     * @param candidateId must not be null
     * @throws IllegalArgumentException in case the given {@literal id} is {@literal null}
     * @throws EmptyResultDataAccessException in case the {@literal candidate} not exists
     */
    void deleteById(UUID candidateId);

    /**
     * Find a candidate by the given id.
     *
     * @param candidateId must not be null
     * @return an optional candidate
     * @throws IllegalArgumentException if {@literal id} is {@literal null}
     */
    Optional<Candidate> findById(UUID candidateId);
}
