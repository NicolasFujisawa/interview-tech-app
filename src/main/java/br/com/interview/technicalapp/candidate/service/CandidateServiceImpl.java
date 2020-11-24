package br.com.interview.technicalapp.candidate.service;

import br.com.interview.technicalapp.candidate.model.Candidate;
import br.com.interview.technicalapp.candidate.repository.CandidateRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Override
    public List<Candidate> findAll() {
        return this.candidateRepository.findAll();
    }

    @Override
    public Candidate save(Candidate candidate) {
        return this.candidateRepository.save(candidate);
    }

    @Override
    public void deleteById(UUID candidateId) {
        this.candidateRepository.deleteById(candidateId);
    }

    @Override
    public Optional<Candidate> findById(UUID candidateId) {
        return this.candidateRepository.findById(candidateId);
    }
}
